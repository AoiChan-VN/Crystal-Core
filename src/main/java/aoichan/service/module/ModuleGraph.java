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
        Set<Module> stack = new HashSet<>();

        for (Module m : graph.keySet()) {
            dfs(m, visited, stack, sorted);
        }

        return sorted;
    }

    private void dfs(Module m, Set<Module> visited, Set<Module> stack, List<Module> sorted) {
        if (stack.contains(m)) {
            throw new IllegalStateException("Cycle detected in ModuleGraph: " + m.getClass().getSimpleName());
        }

        if (visited.contains(m)) return;

        stack.add(m);

        for (Module dep : graph.getOrDefault(m, Collections.emptyList())) {
            dfs(dep, visited, stack, sorted);
        }

        stack.remove(m);
        visited.add(m);
        sorted.add(m);
    }
}
