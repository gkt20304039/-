package th;

public class ResBean extends UserBean {
    public void setAllContents(String id, String name, String date, int num, String text){
        setId(id);
        setName(name);
        setDate(date);
        setNum(num);
        setText(text);
    }
}