# camel-xtokenize-processing-error
Demo project to demonstrate problem with splitting XML using apache camel split with xtokenize.

## Problem Description

We want to process an XML file and split it up into smaller XML files. For this we want to extract all values that are inside a specific tag if they are 
within a namespace that we are interested in. Other namespace should be ignored.

Example input file:
```xml
<root xmlns="http://example.com/" xmlns:ns1="http://example.com/ns1" xmlns:ns2="http://example.com/ns2">
    <value>
        <ns1:select1>test 1</ns1:select1>
    </value>
    <value>
        <ns2:ignore>test 2</ns2:ignore>
    </value>
    <value>
        <ns1:select2>test 3</ns1:select2>
    </value>
</root>
```

For this example the output would be 2 XML files:
```xml
<ns1:select1 xmlns="http://example.com/" xmlns:ns2="http://example.com/ns2" xmlns:ns1="http://example.com/ns1">test 1</ns1:select1>
```
```xml
<ns1:select2 xmlns="http://example.com/" xmlns:ns2="http://example.com/ns2" xmlns:ns1="http://example.com/ns1">test 3</ns1:select2>
```
The value within ns2 is ignored in this case.

However, what we notice is that processing seems to stop completely once it detects the ns2, giving as output only the following XML: 
```xml
<ns1:select1 xmlns="http://example.com/" xmlns:ns2="http://example.com/ns2" xmlns:ns1="http://example.com/ns1">test 1</ns1:select1>
```