package ru.dpankratov.projects.takeoverplanet.Client;

import java.util.List;

import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.GameScreen;

public class LocalBot extends AbstractClient {
    private String name;
    private String id;
    private boolean isStarted = false;
    private final LocalBotLogic localBotLogic = new LocalBotLogic();

    public LocalBot(GalaxyModel galaxy, String name, String id){
        super(galaxy);
        this.name = name;
        this.id = id;
        this.isStarted = true;
    }

    public String getDisplayName() {
        return name;
    }

    public String getUid() {
        return id;
    }

    @Override
    public void run() {
        while (GameScreen.isStarted && isStarted) {
            try {
                Thread.sleep(1000); //TODO: Вынести в настройки
                GalaxyModel _g = getGalaxy();
                if (_g.getPlanetModels().values().stream().filter(p->p.ownerId == getUid()).count()>0) {
                    List<ClientAction> actions = localBotLogic.whatToDo(getGalaxy(), getUid());
                    for (ClientAction a : actions) {
                        SendDrones(a.getFrom(), a.getTo());
                    }
                }else{
                    isStarted = false;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
