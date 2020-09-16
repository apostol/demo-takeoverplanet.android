package ru.dpankratov.projects.takeoverplanet.Client;

import java.util.ArrayList;
import java.util.List;

import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Screens.GameScreen;

public class LocalBot extends AbstractClient {
    private String name;
    private String id;
    private final LocalBotLogic localBotLogic = new LocalBotLogic();

    public static List<LocalBot> poolBots = new ArrayList<LocalBot>();

    public LocalBot(GalaxyModel galaxy, String name, String id){
        super(galaxy);
        this.name = name;
        this.id = id;
        poolBots.add(this);
    }

    public String getDisplayName() {
        return name;
    }

    public String getUid() {
        return id;
    }

    @Override
    public void run() {
        while (!GalaxyLogicRules.isGameOver() && isStarted) {
            try {
                Thread.sleep(1000); //TODO: Вынести в настройки
                GalaxyModel _g = getGalaxy();
                if (_g.getPlanetModels().values().stream().filter(p->p.ownerId == getUid()).count()>0) {
                    List<ClientAction> actions = localBotLogic.whatToDo(getGalaxy(), getUid());
                    for (ClientAction a : actions) {
                        SendDrones(a.getFrom(), a.getTo(), a.getCount() );
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

    @Override
    public void Start() {
        isStarted = true;
        new Thread(this).start();
    }

    @Override
    public void Stop() {
        isStarted = false;
    }
}
