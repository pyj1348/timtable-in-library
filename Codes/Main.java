import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends JFrame {
    private final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/timetable?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "1234";
    private final static String DELIMITER = "\\s*\\t\\s*|\\s*,\\s*|\\s*\\r\\n\\s*";

    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JPanel pnTimetable;

    Main (){
        this.setTitle("중앙대학교 도서관 근로학생 시간표 관리 프로그램  ver. 1.0");
        this.setSize(1800, 1000);
        this.setLocationRelativeTo(null); // Center the frame
        this.setLayout(new BorderLayout());

        /*      Title      */

        JLabel lbTitle = new JLabel("도서관 근로학생 종합시간표",Label.LEFT);
        lbTitle.setFont(lbTitle.getFont().deriveFont(40f));
        Dimension lbTitleSize = lbTitle.getPreferredSize();
        lbTitle.setSize(lbTitleSize);
        this.add(lbTitle, BorderLayout.NORTH);


        /*      Timetable       */
        gbl = new GridBagLayout();
        pnTimetable = new JPanel();
        pnTimetable.setLayout(gbl);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbAdd(new Label("시간", Label.CENTER), 0,0, 1, 1, 0.0, 0.0);
        gbAdd(new Label("월요일", Label.CENTER), 1,0, 1, 1,1.0, 0.0);
        gbAdd(new Label("화요일", Label.CENTER), 2,0, 1, 1, 1.0, 0.0);
        gbAdd(new Label("수요일", Label.CENTER), 3,0, 1, 1, 1.0, 0.0);
        gbAdd(new Label("목요일", Label.CENTER), 4,0, 1, 1, 1.0, 0.0);
        gbAdd(new Label("금요일", Label.CENTER), 5,0, 1, 1, 1.0, 0.0);

        gbAdd( new Label("09:00 ~ 10:00", Label.CENTER), 0,1, 1, 1, 0.0, 1.0);
        gbAdd(new Label("10:00 ~ 11:00", Label.CENTER), 0,2, 1, 1, 0.0, 1.0);
        gbAdd(new Label("11:00 ~ 12:00", Label.CENTER), 0,3, 1, 1, 0.0, 1.0);
        gbAdd(new Label("12:00 ~ 13:00", Label.CENTER), 0,4, 1, 1, 0.0, 1.0);
        gbAdd(new Label("13:00 ~ 14:00", Label.CENTER), 0,5, 1, 1, 0.0, 1.0);
        gbAdd(new Label("14:00 ~ 15:00", Label.CENTER), 0,6, 1, 1, 0.0, 1.0);
        gbAdd(new Label("15:00 ~ 16:00", Label.CENTER), 0,7, 1, 1, 0.0, 1.0);
        gbAdd(new Label("16:00 ~ 17:00", Label.CENTER), 0,8, 1, 1, 0.0, 1.0);
        gbAdd(new Label("17:00 ~ 18:00", Label.CENTER), 0,9, 1, 1, 0.0, 1.0);
        gbAdd(new Label("18:00 ~ 19:00", Label.CENTER), 0,10, 1, 1, 0.0, 1.0);
        gbAdd(new Label("19:00 ~ 20:00", Label.CENTER), 0,11, 1, 1, 0.0, 1.0);
        gbAdd(new Label("20:00 ~ 21:00", Label.CENTER), 0,12, 1, 1, 0.0, 1.0);

        JTextArea[][] table = new JTextArea[12][5];
        for(int i=0; i<12; i++){
            for(int j=0;  j<5; j++){
                table[i][j] = new JTextArea();
                table[i][j].setSize(100, 100);
               // table[i][j].disable();

                gbAdd(table[i][j], j+1, i+1, 1, 1, 0.0, 0.0);
            }
        }

        //table[1][1].setText("권준환 김성한 김유진 김지원 여인혁 이금용 이상준 이승문\n" +
                //"이은희 이재광 조은샘 최영재 ");

        this.add(pnTimetable, BorderLayout.CENTER);

    }
    private void gbAdd(Component c, int x, int y, int gridwidth, int gridheight, double weightx, double weighty) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth  = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = new Insets(1, 1, 1, 1);
        gbl.setConstraints(c, gbc);

        pnTimetable.add(c);
    }



    public static void main(String[] args)
    {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void insertStudent() {
        Connection conn = null;
        Statement stmt = null;
        String data = "20181425   ??   2\r\n" +
                "20154282   ???   4\r\n" +
                "20154851                 ???   4\r\n" +
                "20173286   ???   3\r\n" +
                "20155546   ???   3\r\n" +
                "20183976   ???   2\r\n" +
                "20164603   ???   4\r\n" +
                "20164343   ???   3\r\n" +
                "20170501   ???   3\r\n   " +
                "20134897   ???   3\r\n" +
                "20173700    ???   3\r\n" +
                "20140652   ???   3\r\n" +
                "20183454   ???   2\r\n" +
                "20150526   ???   4\r\n" +
                "20123694   ???     4\r\n" +
                "20144393   ???   4\r\n" +
                "20172458   ???   3\r\n" +
                "20152493   ???   3\r\n" +
                "20131045   ???   4\r\n" +
                "20172509   ???   3\r\n" +
                "20165407   ???   2\r\n" +
                "20154994   ???   3\r\n";

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select * from student");

            while(rset.next()) {
                //System.out.println(rset.getString("name"));
            }
        } catch(SQLException e) {
            System.out.println(e);

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        String[] temp = data.split("\\s*\\r\\n\\s*");
        for(String line : temp) {
            String[] studentInfo = line.split(DELIMITER);
            System.out.println(studentInfo[0] + " | " + studentInfo[1] + " | " + studentInfo[2]);
        }
    }

}