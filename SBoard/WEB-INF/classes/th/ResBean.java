package th;

public class ResBean extends UserBean {
    public void setAllContents(String id, int num, String name, String date, String text){
        setId(id);
        setNum(num);
        setName(name);
        setDate(date);
        setText(text);
    }
}