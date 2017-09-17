public interface testP3FieldEdit 
{
		public String getDCL_Name() throws IllegalFieldValueException;
		public void setDCL_Name(String L_Name);
		public String getDCF_Name() throws IllegalFieldValueException;
		public void setDCF_Name(String F_Name);
		public void appendToStatusArea(String message);
}
