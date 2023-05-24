import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;

public class account {
    private String id_account;
    private double money;
    private String username;
    private String password;
    public String fname;
    public String lname;
    public String email;
    public String phone;

    public List<Map<String, Object>> list_account = new ArrayList<Map<String, Object>>();
    public Map<String, Object> map_account = new HashMap<String, Object>();

    public account() {
        map_account.put("id_account", "AB-1234567");
        map_account.put("money", 1000.00);
        map_account.put("username", "admin");
        map_account.put("password", "admin");
        map_account.put("fname", "Nattawut");
        map_account.put("lname", "rattanabucha");
        map_account.put("email", "erictyth@gmail.com");
        map_account.put("phone", "0640607954");
        list_account.add(map_account);
    }

    public void deActivate () {
        for (Map<String,Object> map : list_account) {
            if(map.get("id_account").equals(getId_account())){
                list_account.remove(map);
                break;
            }
        }
    }

    public String getNameByID(String id_acc) {
        for (Map<String, Object> map : list_account) {
            if (map.get("id_account").equals(id_acc)) {
                return map.get("fname") + " " + map.get("lname");
            }
        }
        return "Not found";
    }

    public boolean check_id_acc(String id_acc) {
        for (Map<String, Object> map : list_account) {
            if (map.get("id_account").equals(id_acc)) {
                return true; // id นี้มีในระบบ
            }
        }
        return false; // id นี้ไม่มีในระบบ
    }

    public void transfer(String id_acc, Float money) {
        for (Map<String, Object> map : list_account) {
            if (map.get("id_account").equals(id_acc)) {
                map.put("money", (Double) map.get("money") + money);
                withdraw(money);
                System.out.println("Your balance is " + getMoney());
                System.out.println("Transfer successfully.");
                break;
            }
        }
    }

    public void withdraw(double money) {
        setMoney(getMoney() - money);
        for (Map<String,Object> map : list_account) {
            if(map.get("id_account").equals(getId_account())){
                map.put("money", getMoney());
            }
        }
    }

    public void deposit(double money) {
        setMoney(money + getMoney());
        for (Map<String,Object> map : list_account) {
            if(map.get("id_account").equals(getId_account())){
                map.put("money", getMoney());
            }
        }
    }

    public boolean login(String username, String password) {
        for (Map<String, Object> map : list_account) {
            if (map.get("username").equals(username) && map.get("password").equals(password)) {
                setId_account((String) map.get("id_account"));
                setMoney(Double.valueOf(map.get("money").toString()));
                setUsername((String) map.get("username"));
                setPassword((String) map.get("password"));
                this.fname = (String) map.get("fname");
                this.lname = (String) map.get("lname");
                this.email = (String) map.get("email");
                this.phone = (String) map.get("phone");
                return true;
            }
        }
        return false;
    }

    public boolean register(
            String fname,
            String lname,
            String username,
            String password,
            String email,
            String phone) {
        try {
            setId_account(generate_id_acc());
            setMoney(1000.00);
            setUsername(username);
            setPassword(password);
            this.fname = fname;
            this.lname = lname;
            this.email = email;
            this.phone = phone;

            map_account = new HashMap<String, Object>();
            map_account.put("id_account", getId_account());
            map_account.put("money", getMoney());
            map_account.put("username", getUsername());
            map_account.put("password", getPassword());
            map_account.put("fname", fname);
            map_account.put("lname", lname);
            map_account.put("email", email);
            map_account.put("phone", phone);
            list_account.add(map_account);

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean check_unique_id(String id_acc) {
        for (Map<String, Object> map : list_account) {
            if (map.get("id_account").equals(id_acc)) {
                return false; // มี id นี้แล้ว
            }
        }
        return true; // ยังไม่มี id นี้
    }

    public boolean check_unique_username(String username) {
        for (Map<String, Object> map : list_account) {
            if (map.get("username").equals(username)) {
                System.out.println("Username already exists.");
                return false; // มี username นี้แล้ว
            }
        }
        return true; // ยังไม่มี username นี้
    }

    public String generate_id_acc() {
        int max = 9999999;
        int min = 1;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        if (check_unique_id("AB-" + rand)) {
            return "AB-" + rand;
        } else {
            return generate_id_acc();
        }
    }

    public String getId_account() {
        return id_account;
    }

    public void setId_account(String id_account) {
        this.id_account = id_account;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
