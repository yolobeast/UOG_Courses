All: libpm.a yadc tests JNIwrapper javamake createfile
trytests: libpm.a tests
clean:
	@rm *.o *.class *.log libJNIpm.so libpm.a example1 yadc create

libpm.a: ParameterManager.o ParameterList.o hash.o
	ar cr libpm.a ParameterManager.o ParameterList.o hash.o
	ranlib libpm.a

createfile:
	gcc creatfile.c -o create

yadc: yacc.o lex.o ParameterList.o hash.o
	gcc lex.o yacc.o ParameterList.o hash.o -o yadc -ll -ly

lex.o: yadc.l
	lex yadc.l
	gcc lex.yy.c -c -o lex.o

yacc.o: yadc.y
	yacc yadc.y -d -y
	gcc y.tab.c -c -o yacc.o

tests: example1
example1: example1.c libpm.a
	gcc example1.c -o example1 -L. -lpm

javamake: Main.java
	javac Main.java

JNIwrapper: JNIwrapper.java JNIwrapper.h libpm.a
	gcc JNIwrapper.c -c -fPIC -I/usr/lib/jvm/java-1.6.0-openjdk-amd64/include -I/usr/lib/jvm/java-1.6.0-openjdk-amd64/include/linux
	gcc -shared -Wl,-soname,libJNIpm.so -I/path/to/java/include -I/path/to/java/include/linux -o libJNIpm.so JNIwrapper.o -L. -lpm
	javac JNIwrapper.java

ParameterManager.o: ParameterManager.c ParameterManager.h ParameterList.h hash.c
	gcc -fPIC ParameterManager.c -c
ParameterList.o: ParameterList.c ParameterList.h
	gcc -fPIC ParameterList.c -c
hash.o: hash.c hash.h
	gcc -fPIC hash.c -c