/*
 * ------------------------------------------------------------------
 *  hash.c - creat the table
 *
 *  CIS*2750 - Software Systems Development and Integration - W15
 *  School of Computer Science
 *  University of Guelph
 *  Author: Sathi Ranganatahan
 * ------------------------------------------------------------------
 */

%{
#include <stdio.h>
#include <stdlib.h>
#include "ParameterList.h"
#include "hash.h"
char * title= NULL;
ParameterList * buff = NULL;
ParameterList * feilds = NULL;
ParameterList * buttons = NULL;
table_h*tablevalues=NULL;
int yyerror( char *);
%}

%union
{
	char *sval;
}
/* Token definitions go here */
%token EQUALS;
%token SIMCOLONE;
%token QUOTES;
%token L_OPEN;
%token L_CLOSE;
%token COMMA;
%token <sval> WORD;
%token  <sval> TITLE;
%token <sval> FIELDS
%token <sval> BUTTONS
%type <sval> expr
%type <sval> string
%%

stmnt : stmnt assign
      | assign;
      
string : WORD
	| TITLE 
	| FIELDS 
	| BUTTONS ;

assign : TITLE EQUALS expr { 
			      if (title==NULL)
			      {
				title = $3;
			      }
			      else
			      {
				return yyerror("title already assigned.");
			      }
			   }
	| FIELDS EQUALS listexpr {
				    if(feilds==NULL)
				    {
				      feilds = buff;
				      buff=NULL;
				    }
				    else
				    {
				      return yyerror("field list already assigned.");
				    }
				 }
	| BUTTONS EQUALS listexpr{
				    if(buttons==NULL)
				    {
				      buttons = buff;
				      buff=NULL;
				    }
				    else
				    {
				      return yyerror("button list already assigned.");
				    }
				 }
	| WORD EQUALS expr {
				    setTable(tablevalues,$1,$3);
			   };
			      

expr : QUOTES string QUOTES SIMCOLONE	{ $$ = $2;};
listexpr : L_OPEN listitem SIMCOLONE ;

listitem  :QUOTES string QUOTES COMMA listitem { if(buff==NULL)
				    {
				      buff=creatList(); 
				    }
				    strcpy(buff->b[buff->temp],$2);
				   // buff->listthings++;
				    buff->index++;
				    buff->temp++;
				    //list_add_element(buff,$1);
				    //PL_next(buff);
				  }
	   
	   |QUOTES string QUOTES L_CLOSE {  if(buff==NULL)
				    {
				      buff=creatList(); 
				    }
				    strcpy(buff->b[buff->temp],$2);
				    //buff->listthings++;
				    buff->index++;
				    buff->temp++;
				    //list_add_element(buff,$1);
				    // PL_next(buff);
				  }
	   
	   |L_CLOSE		  { if(buff==NULL)
				      {
					buff=creatList(); 
				      }
				  };
%%

extern FILE * yyin;
int yyerror(char *s)
{
	fprintf(stderr, "%s\n", s);
}

int main(int arc,char ** argv)
{
	int i=0;
	int x=0;
	int g=0;
	char * val;
	char * filename =NULL;
	char * folder;
	char * nameoffile;
	FILE * input;
	FILE * outfille;
	char file[4096];
	tablevalues = create_table(20);
	
	filename =argv[1];
	nameoffile = argv[2];
	folder = argv[3];
	
	input = fopen(filename,"r");
	if(input == NULL || filename == NULL)
	{
		fprintf(stderr, "The file '%s' could not be opened for reading ");
		return (-1);
	}
	yyin = input;

	while(!feof(yyin))
	{
		yyparse();
	}
	
	/*check for errore*/
	/*check for if the title is empty*/
	if(title == NULL)
	{
		fprintf(stderr, "title is not there in the file");
		return -1;
	}
	if(feilds == NULL)
	{
		/*check for if the feilds is empty*/
		fprintf(stderr, "fields is not there in the file");
		return -1;
	}
	if(buttons == NULL)
	{
		/*check for if the buttons is empty*/
		fprintf(stderr, "buttons is not ther in the file");
		return -1;
	}
	/*check for if the the thisng in the are correct is empty*/
	/*by using the table get function*/
	while (x<feilds->temp)
	{
	  val = tableGet(tablevalues,feilds->b[x]);
		
		if(val == NULL)
		{
			fprintf(stderr, "feild %s is not initialized \n",feilds->b[x]);
			return -1;
		}
		else if((strcmp(val, "string")==0) && (strcmp(val, "integer")==0) && (strcmp(val, "float")==0))
		{
			fprintf(stderr, "%s is not initialized is to invalid type %s \n ",feilds->b[x],val);
			return -1;
		}
		x++;
	}
	
	/*check to see the buttons is empty*/
	while (i<buttons->temp)
	{
	  val = tableGet(tablevalues,buttons->b[i]);
	  
		if(val == NULL)
		{
			fprintf(stderr, "button %s is not initialized \n",buttons->b[i]);
			return -1;
		}
		i++;
	}
	
	/*Writes the file using lex and yacc*/
	sprintf(file, "%s/%s.java", folder,tableGet(tablevalues,buttons->b[(buttons->temp-1)]));
	outfille = fopen(file, "w");


	fprintf(outfille,"import java.awt.event.*;\n\n");
	fprintf(outfille,"public class %s implements ActionListener\n",tableGet(tablevalues,buttons->b[(buttons->temp-1)]));
	fprintf(outfille,"{\n");
	fprintf(outfille,"\t%sFieldEdit d;\n",nameoffile);
	fprintf(outfille,"\tpublic %s(%sFieldEdit d)",tableGet(tablevalues,buttons->b[(buttons->temp-1)]),nameoffile);
	fprintf(outfille,"\t{\n");
	fprintf(outfille,"\t\tthis.d = d;");
	fprintf(outfille,"\t}\n");
	fprintf(outfille,"\tpublic void actionPerformed(ActionEvent e)");
	fprintf(outfille,"\t{\n");
	fprintf(outfille,"\t\ttry\n");
	fprintf(outfille,"\t\t{\n");
	for(x=0;x<feilds->temp;x++)
	{
	    fprintf(outfille,"\t\t\td.getDC%s();\n",feilds->b[x]);
	}
	fprintf(outfille,"\t\t\td.appendToStatusArea (\"button pressed\");\n");
	fprintf(outfille,"\t\t}\n");
	fprintf(outfille,"\t\tcatch(Exception tr)\n");
	fprintf(outfille,"\t\t{\n");
        fprintf(outfille,"\t\td.appendToStatusArea (\"ERROR\");\n");
	fprintf(outfille,"\t\t}\n");
        fprintf(outfille,"\t}\n");
        fprintf(outfille,"}\n");
	fclose(outfille);
	
	

	sprintf(file, "%s/IllegalFieldValueException.java", folder);
	outfille = fopen(file, "w");
	
	fprintf(outfille,"public class IllegalFieldValueException extends Exception\n");
	fprintf(outfille,"{\n\n");
	fprintf(outfille,"public IllegalFieldValueException()\n");
	fprintf(outfille,"\t{\n");
	fprintf(outfille,"\t\tsuper(); \n");
	fprintf(outfille,"\t}\n");
	fprintf(outfille,"\tpublic IllegalFieldValueException(String message)\n");
	fprintf(outfille,"\t{\n");
	fprintf(outfille,"\t\tsuper(message);\n");
	fprintf(outfille,"\t}\n");
	fprintf(outfille,"}\n");
	fclose(outfille);
	
	sprintf(file, "%s/%sFieldEdit.java",folder, nameoffile);
	outfille = fopen(file, "w");
	
	fprintf(outfille,"public interface %sFieldEdit \n",nameoffile);
	fprintf(outfille,"{\n");
	for(x=0;x<feilds->temp;x++)
	{
		fprintf(outfille,"		public String getDC%s() throws IllegalFieldValueException;\n",feilds->b[x]);
		fprintf(outfille,"		public void setDC%s(String %s);\n",feilds->b[x],feilds->b[x]);
	}
	fprintf(outfille,"		public void appendToStatusArea(String message);\n");
	fprintf(outfille,"}\n");
	fclose(outfille);
	
	
	
	
	sprintf(file, "%s/%s.java",folder, nameoffile);
	outfille = fopen(file, "w");
	
	fprintf(outfille,"import javax.swing.border.BevelBorder;\n");
	fprintf(outfille,"import javax.swing.*;\n");
	fprintf(outfille,"import java.awt.*;\n");
	fprintf(outfille,"public class %s extends JFrame implements %sFieldEdit\n",nameoffile,nameoffile);
	fprintf(outfille,"{\n");
	for(x=(feilds->temp-1);x>=0;x--)
	{
		fprintf(outfille,"\tprivate JTextField %s_text;\n",feilds->b[x]);
	}
	fprintf(outfille,"	\tprivate JTextArea textarea;\n"); 
	fprintf(outfille,"\n");
	fprintf(outfille,"\n");
	fprintf(outfille,"	\tpublic static void main(String[] args) {\n");
	fprintf(outfille,"		\t\t%s gui = new %s();\n",nameoffile,nameoffile);
	fprintf(outfille,"		\t\tgui.setVisible(true);\n");
	fprintf(outfille," 	 \t}\n");
	fprintf(outfille,"\n");
	fprintf(outfille,"	 \tpublic %s()\n",nameoffile);
	fprintf(outfille," 	\t{\n");
	fprintf(outfille," 		\t\tsuper(\"%s\");\n",title);
	fprintf(outfille,"		\t\tsetDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
	fprintf(outfille,"		\t\tsetSize(650,600);\n");
	fprintf(outfille,"		\t\tJPanel mainP = new JPanel(new FlowLayout());\n");
	fprintf(outfille,"		\t\tJPanel yoloP = new JPanel();\n");
	fprintf(outfille,"		\t\tJPanel buttP=new JPanel(new FlowLayout());\n");
	fprintf(outfille,"		\t\tJPanel statusbar = new JPanel();\n");
	fprintf(outfille,"		\t\tJPanel statusP =new JPanel(new BorderLayout());\n");
	fprintf(outfille,"		\t\tstatusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));\n");
	fprintf(outfille,"		\t\tJLabel stdunt = new JLabel(\"Student Id:\");\n");
	fprintf(outfille,"\n");		
	for(x=(feilds->temp-1);x>=0;x--)
	{
		fprintf(outfille,"		\t\tJLabel %s_label = new JLabel(\"%s\");\n",feilds->b[x],feilds->b[x]);
		fprintf(outfille,"		\t\t%s_text = new JTextField(40);\n",feilds->b[x]);
		fprintf(outfille,"		\t\tyoloP.add(%s_label);\n",feilds->b[x]);
		fprintf(outfille,"		\t\tyoloP.add(%s_text);\n",feilds->b[x]);
	}
	fprintf(outfille,"\n");	
	for(i=(buttons->temp-1);i>=0;i--)
	{
		fprintf(outfille,"		\t\tJButton %s_button = new JButton(\"%s\");\n",buttons->b[i],buttons->b[i]);
		fprintf(outfille,"		\t\tbuttP.add(%s_button);\n",buttons->b[i]);
	}
	for(i=0;i<buttons->temp;i++)
	{
		fprintf(outfille,"               \t\t\t%s_button.addActionListener(new %s(this));\n",buttons->b[i],tableGet(tablevalues,buttons->b[i]));
		
	}
		
	fprintf(outfille,"	        \t\ttextarea = new JTextArea(20,40);\n");
	fprintf(outfille,"		\t\ttextarea.setEditable(false);\n");
	fprintf(outfille,"		\t\tJScrollPane scroll = new JScrollPane(textarea);\n");
	fprintf(outfille,"		\t\tscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);\n");
	fprintf(outfille,"		\t\tscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);\n");
	fprintf(outfille,"		\t\tyoloP.setLayout(new BoxLayout(yoloP,BoxLayout.Y_AXIS));\n");
	fprintf(outfille,"		\t\tmainP.add(yoloP);\n");
	fprintf(outfille,"		\t\tmainP.add(buttP);\n");
	fprintf(outfille,"\n");
	fprintf(outfille,"		\t\tstatusbar.setLayout(new BoxLayout(statusbar,BoxLayout.Y_AXIS));\n");
	fprintf(outfille,"		\t\tJLabel  statusLabel = new JLabel();\n");
	fprintf(outfille,"\n");
	fprintf(outfille,"		\t\tstatusLabel.setText(\"status\");\n");
	fprintf(outfille,"		\t\tstatusbar.add(statusLabel);\n");
	fprintf(outfille,"		\t\tstatusbar.add(scroll);\n");
	fprintf(outfille,"		\t\tmainP.add(statusbar);\n");
	fprintf(outfille,"		\t\tadd(mainP);\n");
	fprintf(outfille,"	\t}\n");
				
 	
 	fprintf(outfille,"\n\n");
 	for(x=0;x<feilds->temp;x++)
 	{
				    
				    
		fprintf(outfille,"\t\tpublic String getDC%s() throws IllegalFieldValueException\n",feilds->b[x]);
		fprintf(outfille,"\t\t{\n");
 				
		if(strcmp(tableGet(tablevalues,feilds->b[x]), "integer")==0)
		{
			fprintf(outfille,"\t\ttry\n");
			fprintf(outfille,"\t\t{\n");
			fprintf(outfille,"\t\t\tInteger.parseInt(%s_text.getText());\n",feilds->b[x]);
			fprintf(outfille,"\t\t}\n");
			fprintf(outfille,"\t\tcatch(NumberFormatException e)\n");
			fprintf(outfille,"\t\t{\n");
			fprintf(outfille,"\t\t\tthrow new IllegalFieldValueException(%s_text.getText());\n",feilds->b[x]);
			fprintf(outfille,"\t\t}\n");
		}
	        else if(strcmp(tableGet(tablevalues,feilds->b[x]), "float")==0)
		{
			fprintf(outfille,"\t\ttry\n");
			fprintf(outfille,"\t\t{\n");
			fprintf(outfille,"\t\t\tFloat.parseFloat(%s_text.getText());\n",feilds->b[x]);
			fprintf(outfille,"\t\t}\n");
			fprintf(outfille,"\t\tcatch(NumberFormatException e)\n");
			fprintf(outfille,"\t\t{\n");
			fprintf(outfille,"\t\t\tthrow new IllegalFieldValueException(%s_text.getText());\n",feilds->b[x]);
			fprintf(outfille,"\t\t}\n");
		}
				    
				    
		fprintf(outfille,"    \t\t return(%s_text.getText());\n",feilds->b[x]); 
		fprintf(outfille,"	\t\t}\n");
		fprintf(outfille,"	\t\tpublic void setDC%s(String %s)\n",feilds->b[x],feilds->b[x]);
		fprintf(outfille,"	\t\t{\n");
		fprintf(outfille,"    \t\t%s_text.setText(%s);\n",feilds->b[x],feilds->b[x]); 
		fprintf(outfille,"	\t\t}\n");
 	}
 	fprintf(outfille,"\tpublic void appendToStatusArea(String message)\n");
	fprintf(outfille,"\t{\n");
	fprintf(outfille,"\t\ttextarea.append(message + \"\\n\");\n");
	fprintf(outfille,"\t}\n");
	fprintf(outfille,"}\n");
				
	fclose(outfille);
	fclose(input);
	
	return 0;
}
