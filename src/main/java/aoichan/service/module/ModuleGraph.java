package aoichan.service.module;

import aoichan.module.Module;

import java.util.*;

public class ModuleGraph {

    private final Map<Module, List<Module>> graph = new HashMap<>();

    public void add(Module module, List<Module> depends) {
        graph.put(module, depends);
    }

    public List<Module> resolve() {
        List<Module> sorted = new ArrayList<>();
        Set<Module> visited = new HashSet<>();

        for (Module m : graph.keySet()) {
            dfs(m, visited, sorted);
        }

        return sorted;
    }

    private void dfs(Module m, Set<Module> visited, List<Module> sorted) {
        if (visited.contains(m)) return;
        visited.add(m);

        for (Module dep : graph.getOrDefault(m, Collections.emptyList())) {
            dfs(dep, visited, sorted);
        }

        sorted.add(m);
    }
}
