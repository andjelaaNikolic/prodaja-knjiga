package sistemske.operacije;

import db.repozitorijum.DBRepozitorijum;
import repozitorijum.Repozitorijum;
import repozitorijum.db.impl.DBRepozitorijumGenericki;


public abstract class OpsteSistemskeOperacije {
    
    protected final Repozitorijum broker;

    public OpsteSistemskeOperacije() {
        this.broker = new DBRepozitorijumGenericki();
    }
    
    public final void izvrsi(Object objekat, Object uslov) throws Exception{
        
        try{
        preduslovi(objekat);
        zapocniTransakciju();
        izvrsiOperaciju(objekat,uslov);
        potvrdiTransakciju();}
        catch(Exception e){
            ponistiTransakciju();
            throw e;
        }
        
        
    }

    protected abstract void preduslovi(Object param) throws Exception; 
    protected  abstract void izvrsiOperaciju(Object param, Object uslov) throws Exception;
    
    private void zapocniTransakciju() throws Exception{
        ((DBRepozitorijum) broker).povezi();
    }

    private void potvrdiTransakciju() throws Exception{
        ((DBRepozitorijum)broker).commit();
    }

    private void ponistiTransakciju()throws Exception {
        ((DBRepozitorijum)broker).rollback();
    }

    private void ugasiKonekciju()throws Exception {
       ((DBRepozitorijum)broker).prekiniVezu();
    }
    
}
