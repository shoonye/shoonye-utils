package shoonye.util.bean;


public class NumberFilter {
    enum Operator{lt,lte,eq,gte,gt,bt}
    private int value;
    private int value2;
    private Operator operator;
    
    private NumberFilter() {
        super();
    }
    
    public static NumberFilter lt(int value){
        return new NumberFilter(value, Operator.lt);
    }
    
    public static NumberFilter le(int value){
        return new NumberFilter(value, Operator.lte);
    }
    
    public static NumberFilter eq(int value){
        return new NumberFilter(value, Operator.eq);
    }
    
    public static NumberFilter gt(int value){
        return new NumberFilter(value, Operator.gt);
    }
    
    public static NumberFilter ge(int value){
        return new NumberFilter(value, Operator.gte);
    }
    
    public static NumberFilter bt(int value,int value2){
        return new NumberFilter(value, value2);
    }

    private NumberFilter(int value, Operator op) {
        super();
        this.value = value;
        this.operator = op;
    }
    
    private NumberFilter(int value, int value2) {
        super();
        this.value = value;
        this.operator = Operator.bt;
        this.value2=value2;
    }

    
    public int getValue() {
        return value;
    }

    
    public void setValue(int value) {
        this.value = value;
    }

    
    public int getValue2() {
        return value2;
    }

    
    public void setValue2(int value2) {
        this.value2 = value2;
    }

    
    public Operator getOperator() {
        return operator;
    }

    
    public void setOperator(Operator op) {
        this.operator = op;
    }
    
    public String jongoQuery(String fieldName){
        StringBuilder query = new StringBuilder(fieldName);
        if(Operator.eq.equals(operator)){
            query.append(":");
            query.append(value);
        }else if(Operator.bt.equals(operator)){
            query.append(" : { $gte :");
            query.append(value);
            query.append(", $lte :");
            query.append(value2);
            query.append("}");
        }else{
            query.append(" : { $");
            query.append(operator);
            query.append(":");
            query.append(value);
            query.append("}");
        }
        return query.toString();
    }
    
}
