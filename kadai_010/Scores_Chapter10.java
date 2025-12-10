package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
	     Connection con = null;
	        Statement stmt = null;

	        try {
	            // (1) データベースに接続
	            con = DriverManager.getConnection(
	            		 "jdbc:mysql://localhost:3306/challenge_java?useSSL=false&serverTimezone=UTC",
	                "root",
	                "hsubmed14"   // ←自分のMySQLパスワードに変更
	            );
	            System.out.println("データベース接続成功：" + con);

	            // (2) SQL実行準備
	            stmt = con.createStatement();

	            // (3) データ更新処理
	            System.out.println("レコード更新を実行します");

	            String updateSql =
	                "UPDATE scores " +
	                "SET score_math = 95, score_english = 80 " +
	                "WHERE name = '武者小路勇気'";

	            int updateCount = stmt.executeUpdate(updateSql);
	            System.out.println(updateCount + "件のレコードが更新されました");

	            // (4) 並び替えSQL
	            System.out.println("数学・英語の点数が高い順に並べ替えました");

	            String selectSql =
	                "SELECT * FROM scores " +
	                "ORDER BY score_math DESC, score_english DESC";

	            ResultSet rs = stmt.executeQuery(selectSql);

	            // (5) 結果表示
	            int num = 1;
	            while (rs.next()) {
	                System.out.println(
	                    num + "件目：生徒ID=" + rs.getInt("id") +
	                    "／氏名=" + rs.getString("name") +
	                    "／数学=" + rs.getInt("score_math") +
	                    "／英語=" + rs.getInt("score_english")
	                );
	                num++;
	            }

	        } catch (SQLException e) {
	            System.out.println("エラー発生：" + e.getMessage());
	        } finally {
	            try {
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                System.out.println("クローズエラー：" + e.getMessage());
	            }
	        }
	    }
	}