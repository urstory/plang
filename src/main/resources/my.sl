int a;
int b;
int c;
int result;

a = 10;
b = 20;
c = 30;

print(a);
print(b);
print(c);

if (a + b > c) {
    result = a * b;
} else {
    result = a + c;
}
print(result);

int sum;
sum = a + b + c;
print(sum);

int product;
product = a * b * c;
print(product);

if (sum > product) {
    result = sum;
} else {
    result = product;
}
print(result);