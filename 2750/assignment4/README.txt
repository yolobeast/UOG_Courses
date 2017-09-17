Sathi.Ranganathan
0757619


----------------------------------------------
1.Problem

- Have to creat a library to that takes in a text file that contains the 
  inforation and parse it out.
- The parsed infromation should be stored in a union, and be able to access it back
- Now with given the same problem but now pasred it twice and by using JNI conver the main function from c to Java
- where java will call GUI function to show how it will look like  
- The GUI will hava  kind of compilers one for normal parameter manager and the other one is for Lex and yac
- write an installation script that will instal the file and be able to run the file  
------------------------------------------------
2.Limation

- The Parsing is not perfect and it will give some error 
- After installing the terminal has to be closed to work with IDE compiler
- all the parse error working except whane the ther is empty list and there is action listiner is given to it 
- I am implemanting my own action lisner so if you try to put your own action listner it will get overide

--------------------------------------------------
3.Install

- bash install_dialog to build and install the file 
- bash install_dialog --build 
  - to decode and make the file 
- bash install_dialog --install
  - to enter a folder name and move the lib and bin folder in there
- inorder to run my program 
  - cd folder you have created
  - cd bin 
  - java Dialog
  - to compile click on compile button 
  - to compile and run click on compile and run button 
  - in order to change the compile option  click on congif and choose change compilers
  - to close click on stop button or (ctrl - Q)
  - to save (ctrl s)
  - to save ass (ctrl shit s)
  - to open (ctrl O)
  - to help (F1)


------------------------------------------------------------
4. Testing 

- Tested out most of the cases 
   - if the list is empty it give pasre error
   - if there is nothin in the file thaen it will provide parse error
   - there was a porblem that i fixed it when using the tab 
   - check to see if the JNIwrapper class working perfectly using soem test caes
   - check too see if the TA test cases are working 
   - saving without .config will give you an pop up to to save it as .config
   - I have written my own action listiner but it dosnt have data base support
      - My own action listiner will appand for these buttons get,set,add,query,update,delete
   - For running IDE you have to close the Terminal 
   - no error from install
   - working directory is fully functional 
---------------------------------------------------------
5.collboration

- I have collborated with James.Maher,Josh.Pinsent, Kabir.Singh and Sameer Gulamali.
- I have used 
  - http://www.sparknotes.com/cs/searching/hashtables/section3.rhtml - I used this website to create my hash table 
  - https://www.youtube.com/watch?v=MfhjkfocRR0&list=PLTxllHdfUq4f7-uHOpxXnBUbsuLiI9pmb - I used his videos to understand the hash table 
  - https://github.com/Bananattack/uoguelph/tree/master/CIS2750/A1 -  I used this website get an idea how to do the make file and JNI and lex and yacc
  - http://stackoverflow.com/questions/21534515/jfilechooser-open-in-current-directory - I learned how to do file chooser
  - http://stackoverflow.com/questions/10952130/localized-accelerators-jmenuitem-hotkeys-in-swing- used this to understand hwo to do hot keys
  - http://docs.oracle.com/javase/tutorial/uiswing/components/button.html - learned how to use radio keys 
  - http://tldp.org/LDP/abs/html/fto.html - leaned how to do the file in bash 
  - http://tldp.org/LDP/abs/html/sample-bashrc.html - need to knw how to deal with barshrc

---------------------------------------------------------
6.Code Refrencing 

- http://troydhanson.github.io/uthash/
- http://en.wikipedia.org/wiki/Hash_table
- http://www.thelearningpoint.net/computer-science/data-structures-queues--with-c-program-source-code
- http://stackoverflow.com/questions/19352066/java-jni-application-crash-due-to-stack-overflow
- https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html
