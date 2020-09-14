package ru.dpankratov.projects.takeoverplanet.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientCommand {

    public ClientCommand(List<ClientAction> actions){
        this.actions = actions;
    }
    private List<ClientAction> actions = new ArrayList<>();
    public List<ClientAction> getActions() {
        return actions;
    }
    public void setActions(List<ClientAction> actions) {
        this.actions = actions;
    }
}
