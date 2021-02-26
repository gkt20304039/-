package th;

public class ResSarchBean extends UserBean {
    public void setAllContents(String id, int num, String name, String date, String text){
        setId(id);
        setNum(num);
        setName(name);
        setDate(date);
        setText(text);
    }
}