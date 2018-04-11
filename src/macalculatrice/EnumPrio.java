package macalculatrice;

/**
 *
 * @author gauthier
 */
public enum EnumPrio {
    PAR(1,"("),
    MUL(2,"*"),
    DIV(3,"/");
    
    int code;
    String label;
    
    EnumPrio(int id, String value)
    {
        this.code = id;
        this.label = value;
    }
    
    public String toString()
    {
        return this.label;
    }
}