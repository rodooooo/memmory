import java.util.Vector;

public class PageFault {
    // Método simple de reemplazo: encuentra un marco libre o reemplaza el primero
    public static void replacePage(Vector memVector, int virtPageNum, int requestedPageNum, ControlPanel controlPanel) {
        if (memVector == null || memVector.size() == 0) return;
        Page requested = (Page) memVector.elementAt(requestedPageNum);
        // Buscar un marco físico libre
        int freePhysical = -1;
        for (int i = 0; i < memVector.size(); i++) {
            Page p = (Page) memVector.elementAt(i);
            if (p.physical == -1) {
                freePhysical = i; // using index as candidate physical number is not ideal but keep simple
                break;
            }
        }
        if (freePhysical != -1) {
            requested.physical = freePhysical;
            if (controlPanel != null) controlPanel.addPhysicalPage(requested.id, requested.physical);
            return;
        }
        // Si no hay libre, reemplazar la primera página diferente de requested
        int replaceIdx = -1;
        for (int i = 0; i < memVector.size(); i++) {
            Page p = (Page) memVector.elementAt(i);
            if (p.id != requested.id && p.physical != -1) { replaceIdx = i; break; }
        }
        if (replaceIdx == -1) {
            // nada que reemplazar
            return;
        }
        Page victim = (Page) memVector.elementAt(replaceIdx);
        int victimPhysical = victim.physical;
        // quitar mapping del victim
        victim.physical = -1;
        if (controlPanel != null) controlPanel.removePhysicalPage(victimPhysical);
        // mapear requested
        requested.physical = victimPhysical;
        if (controlPanel != null) controlPanel.addPhysicalPage(requested.id, requested.physical);
    }
}