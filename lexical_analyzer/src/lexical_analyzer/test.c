int add(int a, int b) {
	int c;
	c = a + b;

	return c;
}

int add10(int a){
	int c = 10;

	while( c != 0){
		a = a + 1;
		c = c - 1;
	}

	return a;
}

int main(int arg1, char arg2){
	int a, b;
	int c;
	char str;
	a=10;
	b=20;
	c = add(a,b);

	if (c > 10 ) {
		str = "A";
	}
	else
		str = "B";

	a = add10(a);
}
