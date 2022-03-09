package fr.epsi.beans;

public class FicheAgent {
    private String agent_name;
    private String agent_username;
    private String mission;
    private String password;
    private Materiel materiel;

    public FicheAgent(Agent agent_name, Agent agent_username, String mission, String password, Materiel materiel) {
        this.agent_name = agent_name.getName();
        this.agent_username = agent_username.getUsername();
        this.mission = mission;
        this.password = password;
        this.materiel = materiel;
    }
}
