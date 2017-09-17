public interface testP2FieldEdit 
{
		public String getDClag() throws IllegalFieldValueException;
		public void setDClag(String lag);
		public String getDClike() throws IllegalFieldValueException;
		public void setDClike(String like);
		public String getDCasdf() throws IllegalFieldValueException;
		public void setDCasdf(String asdf);
		public String getDCstud() throws IllegalFieldValueException;
		public void setDCstud(String stud);
		public void appendToStatusArea(String message);
}
