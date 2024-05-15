package Entity.NPC;

/**
 * NPC
 */
public abstract class NPC {
    
    protected String name;
    protected String job;

    public String getName() {
        return this.name;
    }    

    public String getJob() {
        return this.job;
    }


    public NPC(String name, String job) {
        this.name = name;
        this.job = job;
    }

    // public void doJob(){

    // };

    
}