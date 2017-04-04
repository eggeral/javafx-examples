package egger.software.baylandtag.ag_dummy_person_table;

public class Member {

	private String surname;
	private String forename;
	private String title;

	public Member(String surname, String forename, String title) {
		this.surname = surname;
		this.forename = forename;
		this.title = title;
	}

	/*
	 * `Beruf` varchar(100) DEFAULT NULL, `Bild` mediumblob, `geb_am` date
	 * DEFAULT NULL, `geb_in` varchar(100) DEFAULT NULL, `ges_am` date DEFAULT
	 * NULL, `ges_in` varchar(100) DEFAULT NULL, `f_id` int(11) DEFAULT NULL,
	 * `k_id` int(11) DEFAULT NULL,
	 */

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
