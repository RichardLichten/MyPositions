public class LineStyles {
 

    private String name;

 
    public LineStyles() {
 
    }
 
    public LineStyles(String name) {
     
        this.name = name;
    }
 
   
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
 
    @Override
    public String toString() {
        return this.name;
    }
 
}