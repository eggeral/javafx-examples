package egger.software.baylandtag.au_display_image;

import javafx.scene.image.Image;

public class Member {

	private Integer id;
	private String surname;
	private String forename;
	private String title;
	private Confession confession;
	private MaritalStatus maritalStatus;
	private Image image;

	public Member(Integer id, String surname, String forename, String title, Confession confession, MaritalStatus maritalStatus) {
		this(id, surname, forename, title, null, confession, maritalStatus);
	}

	public Member(Integer id, String surname, String forename, String title, Image image, Confession confession,
			MaritalStatus maritalStatus) {

		this.id = id;
		this.surname = surname;
		this.forename = forename;
		this.title = title;
		this.image = image;
		this.confession = confession;
		this.maritalStatus = maritalStatus;

	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Confession getConfession() {
		return confession;
	}

	public void setConfession(Confession confession) {
		this.confession = confession;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus martialStatus) {
		this.maritalStatus = martialStatus;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confession == null) ? 0 : confession.hashCode());
		result = prime * result + ((forename == null) ? 0 : forename.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (confession == null) {
			if (other.confession != null)
				return false;
		} else if (!confession.equals(other.confession))
			return false;
		if (forename == null) {
			if (other.forename != null)
				return false;
		} else if (!forename.equals(other.forename))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maritalStatus == null) {
			if (other.maritalStatus != null)
				return false;
		} else if (!maritalStatus.equals(other.maritalStatus))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", surname=" + surname + ", forename=" + forename + ", title=" + title
				+ ", confession=" + confession + ", maritalStatus=" + maritalStatus + "]";
	}

}
