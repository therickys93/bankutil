# Bank Util

[![Build Status](https://travis-ci.org/therickys93/bankutil.svg?branch=master)](https://travis-ci.org/therickys93/bankutil)

This library is for my personal use. If you want to use it you're welcome!

## Install using gradle

I don't use standard repositories so you have to use Jitpack.

* First in your root build.gradle file add this:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

* Finally in your dependencies add:

```
compile 'com.github.therickys93:bankutil:1.1.1'
```

## content

There is only one class: Iban
This class will check if the Italian Iban that you pass is valid or not

```
Iban iban = Iban.checkIban("IT40S0542811101000000123456");
boolean valid = iban.correct();
String iban = iban.iban();
iban.toString();   // Iban={iban=IT40S0542811101000000123456, correct=true}
```
