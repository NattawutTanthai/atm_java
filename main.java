import java.util.Scanner;
import java.text.DecimalFormat;

public class main {
    static boolean isLogin = false;
    static boolean isDeActive = false;
    static account acc = new account();
    static DecimalFormat df = new DecimalFormat("#,###.00");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        clearScreen();
        for (int i = 0; i < 1;) {
            String num = "";
            try {
                login_regis_menu();
                while (true) {
                    System.out.print("Enter your choice Login : ");
                    num = scanner.nextLine();
                    if (num.equals("1")) {
                        login();
                        if (isLogin) {
                            menu();
                        }
                        break;
                    } else if (num.equals("2")) {
                        register();
                        break;
                    } else if (num.equals("3")) {
                        System.exit(0);
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
            } catch (Exception e) {
                scanner.next();
            }

        }

    }

    static void menu() {
        Scanner scanner_menu = new Scanner(System.in);
        // clearScreen();
        System.out.println("<><><><><> Menu <><><><><>");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Deactivate account");
        System.out.println("5. My Account");
        System.out.println("6. Log out");
        System.out.println("<><><><><><><><><><><><><>");

        boolean flag = true;
        while (flag) {
            System.out.print("Enter your choice Menu : ");
            String num = "";
            try {
                num = scanner_menu.nextLine();
            } catch (Exception e) {
                scanner_menu.next();
            }
            if (num.equals("1")) {
                deposit();
                // clearScreen();
                menu();
            } else if (num.equals("2")) {
                withdraw();
                // clearScreen();
                menu();
            } else if (num.equals("3")) {
                transfer();
                // clearScreen();
                menu();
            } else if (num.equals("4")) {
                deactivate();
                if (isDeActive) {
                    flag = false;
                } else {
                    menu();
                }
                // clearScreen();
            } else if (num.equals("5")) {
                myAccount();
                menu();
            } else if (num.equals("6")) {
                logout();
                flag = false;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    static void logout() {
        System.out.println("Logout successfully.");
        main(null);
    }

    static void myAccount() {
        Scanner scanner_myAccount = new Scanner(System.in);
        clearScreen();
        System.out.println("<><><><><> My Account <><><><><>");
        System.out.println("ID_Account : " + acc.getId_account());
        System.out.println("Money : " + df.format(acc.getMoney()) + " Baht");
        System.out.println("Name : " + acc.fname + " " + acc.lname);
        System.out.println("Username : " + acc.getUsername());
        System.out.println("Email : " + acc.email);
        System.out.println("Phone : " + acc.phone);
        System.out.println("<><><><><><><><><><><><><><><><>");
    }

    static void deactivate() {
        Scanner scanner_deactivate = new Scanner(System.in);
        clearScreen();
        System.out.println("<><><><><> Deactivate <><><><><>");
        System.out.println("Are you sure you want to deactivate your account?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        boolean flag = true;
        while (flag) {
            System.out.print("Enter your choice Deactivate : ");
            int num = 0;
            try {
                num = scanner_deactivate.nextInt();
            } catch (Exception e) {
                scanner_deactivate.next();
            }
            if (num == 1) {
                acc.deActivate();
                System.out.println("Deactivate success.");
                isDeActive = true;
                flag = false;
            } else if (num == 2) {
                flag = false;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

    }

    static void transfer() {
        Scanner scanner_transfer = new Scanner(System.in);
        clearScreen();
        String nameToTransfer = "";
        String id_acc = "";
        System.out.println("<><><><><> Transfer <><><><><>");
        System.out.println("<><><><><> [00] Back <><><><><>");

        while (true) {
            System.out.print("Enter ID Account : ");
            id_acc = scanner_transfer.nextLine();
            if (id_acc.equals("00")) {
                return;
            }
            id_acc.trim();
            if (acc.check_id_acc(id_acc)) {
                nameToTransfer = acc.getNameByID(id_acc);
                System.out.println("Name : " + nameToTransfer);
                break;
            } else {
                System.out.println("ID Account not found.");
            }
        }

        while (true) {
            System.out.print("Enter amount : ");
            float amount = scanner_transfer.nextFloat();
            if (amount == 00) {
                return;
            }
            if (amount <= 0 || amount > acc.getMoney()) {
                System.out.println("Invalid input. Please try again.");
            } else {
                acc.transfer(id_acc, amount);
                break;
            }
        }

    }

    static void withdraw() {
        Scanner scanner_withdraw = new Scanner(System.in);
        clearScreen();
        System.out.println("><><><><><> Withdraw <><><><><><");
        System.out.println("<><><><><> [00] Back <><><><><>");
        double amount = 0.0;
        boolean flag = true;

        while (flag) {
            try {
                System.out.print("Enter amount : ");
                amount = scanner_withdraw.nextDouble();
                flag = false;
                if (amount == 00) {
                    return;
                }
            } catch (Exception e) {
                scanner_withdraw.next();
                amount = 0.0;
            }
            if (amount <= 0) {
                System.out.println("Invalid input. Please try again.\n");
                withdraw();
            }
        }

        if (amount > acc.getMoney()) {
            System.out.println("Your balance is not enough.");
            System.out.println("Your balance is " + acc.getMoney());
            withdraw();
        }
        acc.withdraw(amount);
        System.out.println("Withdraw success.");
        System.out.println("Your balance is " + df.format(acc.getMoney()));
    }

    static void deposit() {
        Scanner scanner_deposit = new Scanner(System.in);
        clearScreen();
        System.out.println("<><><><><> Deposit <><><><><>");
        System.out.println("<><><><><> [00] Back <><><><><>");
        boolean flag = true;
        Double amount = 0.0;

        while (flag) {
            try {
                System.out.print("Enter amount : ");
                amount = scanner_deposit.nextDouble();
                if (amount == 00) {
                    return;
                }
                flag = false;
            } catch (Exception e) {
                scanner_deposit.next();
                amount = 0.0;
            }
            if (amount <= 0) {
                System.out.println("Invalid input. Please try again.");
            }
        }
        acc.deposit(amount);
        System.out.println("Deposit success.");
        System.out.println("Your balance is " + df.format(acc.getMoney()));

    }

    static void login_regis_menu() {
        System.out.println("><> Protoss bank <><");
        System.out.println("<><><><><><><><><><>");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("<><><><><><><><><><>");
    }

    static void login() {
        Scanner scanner_log = new Scanner(System.in);
        // clearScreen();
        System.out.println("<><><><> Login <><><><>");
        System.out.println("<><><> [//] Back <><><>");
        System.out.print("Username : ");
        String username = scanner_log.nextLine();
        username.trim();
        if (check_back(username))
            return;
        System.out.print("Password : ");
        String password = scanner_log.nextLine();
        password.trim();
        if (check_back(password))
            return;

        if (acc.login(username, password)) {
            System.out.println("* Login success. *");
            isLogin = true;
        } else {
            System.out.println("* Login failed. *");
            login();
        }
    }

    static boolean check_back(String input) {
        if (input.equals("//")) {
            return true;
        } else {
            return false;
        }
    }

    static void register() {
        Scanner scanner_reg = new Scanner(System.in);
        clearScreen();

        System.out.println("<><><> Register <><><>");
        System.out.println("<><><> [//] Back <><><>");

        System.out.print("First name : "); // First name
        String fname = scanner_reg.nextLine();
        fname.trim();
        if (check_back(fname))
            return;

        System.out.print("Last name : "); // Last name
        String lname = scanner_reg.nextLine();
        lname.trim();
        if (check_back(lname))
            return;

        String phone = "";
        do {
            System.out.print("Phone number : "); // Phone number
            phone = scanner_reg.nextLine();
            phone.trim();
            if (check_back(phone))
                return;
        } while (!check_phone(phone));

        String email = "";
        do {
            System.out.print("Email : "); // Email
            email = scanner_reg.nextLine();
            email.trim();
            if (check_back(email))
                return;
        } while (!check_email(email));

        String username = "";
        do {
            System.out.print("Username : "); // Username
            username = scanner_reg.nextLine();
            username.trim();
            if (check_back(username))
                return;
        } while (!acc.check_unique_username(username));

        String password = "";
        String confirm_password = "";
        do {
            System.out.print("Password : "); // Password
            password = scanner_reg.nextLine();
            password.trim();
            if (check_back(password))
                return;

            System.out.print("Confirm Password : "); // Confirm Password
            confirm_password = scanner_reg.nextLine();
            confirm_password.trim();
            if (check_back(confirm_password))
                return;
        } while (!check_password(password, confirm_password));

        if (acc.register(fname, lname, username, password, email, phone)) {
            System.out.println("* Register success *");
        } else {
            System.out.println("Register failed");
        }
    }

    static boolean check_password(String password, String confirm_password) {
        if (password.equals(confirm_password)) {
            return true;
        } else {
            System.out.println("Password not match");
            return false;
        }
    }

    static boolean check_phone(String phone) {
        if (phone.matches("0[0-9]{9}$")) {
            return true;
        } else {
            System.out.println("Phone number input invalid.");
            return false;
        }
    }

    static boolean check_email(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            System.out.println("Email input invalid.");
            return false;
        }
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
