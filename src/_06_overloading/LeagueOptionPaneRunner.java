package _06_overloading;

public class LeagueOptionPaneRunner {
	public static void main(String[] args) {
		LeagueOptionPane lop=new LeagueOptionPane();
		lop.showMessageDialog("MESSAGE");
		lop.showMessageDialog("Second MESSAGE","This is the second title");
		lop.showMessageDialog("Third MESSAGE","This is the third title","leagueDark.png");
	}
}
