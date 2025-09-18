import java.util.*;

/**
 * PageFault - implementación First‑Fit que usa la tabla physToVirt en Kernel.
 *
 * Firma nueva:
 *   replacePage(Vector mem, int virtPageNum, int replacePageNum, ControlPanel controlPanel, Kernel kernel)
 *
 * Uso:
 *  - Itera frames 0..kernel.getPhysicalFrameCount()-1 y usa kernel.getVirtForFrame(frame)
 *    para encontrar la primera ranura ocupada (First‑Fit).
 *  - Si la víctima está marcada M==1, registra un write‑back (simulado).
 *  - Actualiza GUI y mappings usando kernel.mapFrame/unmapFrame.
 *  - Si no hay frames ocupados, hace fallback seguro a replacePageNum.
 */
public class PageFault {

  public static void replacePage(Vector mem, int virtPageNum, int replacePageNum, ControlPanel controlPanel, Kernel kernel) {
    if (mem == null || mem.size() == 0) {
      System.out.println("PageFault: mem vacío, nada que reemplazar.");
      return;
    }
    if (kernel == null) {
      System.out.println("PageFault: kernel nulo, abortando reemplazo.");
      return;
    }

    int frameCount = kernel.getPhysicalFrameCount();
    int selectedFrame = -1;
    int selectedVirtual = -1;

    // First‑Fit: buscar la primera ranura física ocupada (0..frameCount-1)
    for (int f = 0; f < frameCount; f++) {
      int v = kernel.getVirtForFrame(f);
      if (v != -1) {
        selectedFrame = f;
        selectedVirtual = v;
        break;
      }
    }

    // Fallback: si no se encontró usando physToVirt, buscar cualquier página con physical >= 0
    if (selectedVirtual == -1) {
      for (int v = 0; v < mem.size(); v++) {
        Page p = (Page) mem.elementAt(v);
        if (p != null && p.physical >= 0) {
          selectedVirtual = v;
          selectedFrame = p.physical;
          break;
        }
      }
    }

    // Último fallback: usar replacePageNum
    if (selectedVirtual == -1) {
      selectedVirtual = replacePageNum;
      Page fallback = (Page) mem.elementAt(selectedVirtual);
      selectedFrame = (fallback != null) ? fallback.physical : -1;
      System.out.println("PageFault: no se encontró víctima con First‑Fit; fallback a virtual=" + replacePageNum);
    }

    // Sanity
    if (selectedVirtual < 0 || selectedVirtual >= mem.size()) {
      System.out.println("PageFault: índice de página seleccionado inválido: " + selectedVirtual);
      return;
    }

    Page victim = (Page) mem.elementAt(selectedVirtual);
    Page toLoad = (Page) mem.elementAt(replacePageNum);

    System.out.println("PageFault: victim virtual=" + selectedVirtual + " frame=" + selectedFrame + " -> reemplazar con virtual=" + replacePageNum);

    // Si la víctima está sucia -> simular write‑back
    if (victim != null && victim.M == 1) {
      System.out.println("PageFault: Victim (v=" + selectedVirtual + ") está dirty (M=1). Simulando write‑back.");
      // TODO: podrías contabilizar writebacks o simular coste de E/S aquí
    }

    // Remove visual for victim (si existe)
    if (victim != null && victim.physical >= 0) {
      try {
        if (controlPanel != null) controlPanel.removePhysicalPage(victim.physical);
      } catch (Exception e) { /* proteger contra distintas versiones de ControlPanel */ }
    }

    // Reset victim metadata
    if (victim != null) {
      victim.inMemTime = 0;
      victim.lastTouchTime = 0;
      victim.R = 0;
      victim.M = 0;
      victim.physical = -1; // físico será actualizado por kernel.mapFrame si corresponde
    }

    // Si no hay frame disponible (selectedFrame == -1) entonces no hay mapping físico posible
    if (selectedFrame == -1) {
      // Asignar estado a la nueva página como no mapeada (pero la simulación provista antes
      // asumía que siempre hay frames; aquí manejamos la ausencia)
      if (toLoad != null) {
        toLoad.physical = -1;
        toLoad.inMemTime = 0;
        toLoad.lastTouchTime = 0;
        toLoad.R = 0;
        toLoad.M = 0;
      }
      System.out.println("PageFault: no hay frame físico disponible para mapear (selectedFrame == -1).");
      return;
    }

    // Mapear el nuevo page en el frame elegido usando Kernel helpers (mapFrame hará unmap del previo)
    kernel.mapFrame(selectedFrame, replacePageNum);

    // Asegurar metadata de la página cargada
    if (toLoad != null) {
      toLoad.inMemTime = 0;
      toLoad.lastTouchTime = 0;
      toLoad.R = 0;
      toLoad.M = 0;
    }
  }
}