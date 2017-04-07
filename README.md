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
compile 'com.github.therickys93:bankutil:1.2.1'
```

## content

There is only one class: Iban
This class will check if the Italian Iban that you pass is valid or not

```
Iban iban = Iban.createIban("IT40S0542811101000000123456");
iban.iban();   // return the iban
iban.length();   // return true or false
iban.checkDigitsOK();   // return true or false
iban.ibanChecksum();    // return true or false
iban.toString();   // Iban={iban=IT40S0542811101000000123456, correct=true}
```

To test the Iban I take them from this [Iban validator website](https://it.iban.com/). 
