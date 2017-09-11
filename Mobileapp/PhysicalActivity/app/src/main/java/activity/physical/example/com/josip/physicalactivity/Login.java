package activity.physical.example.com.josip.physicalactivity;

/**
 * Created by Josip on 3.8.2017..
 */

public class Login{
  private String username;
  private String password;
  private boolean autoriziran;
  public Login(String username,String password){
      this.username=username;
      this.password=password;
  }
  public String getUsername(){
      return username;
  }
  public String getPassword(){
      return password;
  }

    public boolean isAutoriziran() {
        return autoriziran;
    }

    public void setAutoriziran(boolean autoriziran) {
        this.autoriziran = autoriziran;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
