Real Estate Data Interchange Standard:

Real Estate Transaction Specification
Version 1.7.2

August 29, 2008

Table of Contents

1. Introduction
1-1
Purpose  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 1-1
Scope   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 1-1
Requirements  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 1-1
Required Features   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 1-1
Compatibility with Prior Versions.  .  .  .  .  .  .  .  . 1-2
Terminology  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 1-2

2. Notational Conventions
2-1
Augmented BNF.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 2-1
Typographic Conventions   .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 2-1
Rules .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 2-2
Atoms and Primitive Entities .  .  .  .  .  .  .  .  .  .  .  .  .  . 2-2

3. Message Format
3-1
General Message Format  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 3-1
RETS HTTP/1.1 Encapsulation  .  .  .  .  .  .  .  .  .  .  . 3-1
Request Arguments  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 3-1
Response Bodies  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 3-2
Request Format  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 3-2
Required Client Request Header Fields  .  .  .  .  .  .  . 3-2
Optional Client Request Header Fields   .  .  .  .  .  .  . 3-3
Response Format  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 3-4
Required Server Response Header Fields .  .  .  .  .  . 3-5
Optional Server Response Header Fields  .  .  .  .  .  . 3-6
Data Compression in RETS Transactions   .  .  .  .  .  . 3-7
General Status Codes   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 3-8
Computing the RETS-UA-Authorization Value .  .  . 3-9

4-1
4. Login Transaction
Security  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-1
User Authentication  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-1
Client Authentication  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-1
Data Security.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-1
Authorization Example  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-2
Required Request Arguments .  .  .  .  .  .  .  .  .  .  .  .  . 4-2
Optional Request Arguments  .  .  .  .  .  .  .  .  .  .  .  .  . 4-2
BrokerCode Argument.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-2
SavedMetadataTimestamp Argument.  .  .  .  .  . 4-2
Optional Response Header Fields.  .  .  .  .  .  .  .  .  .  . 4-3
Login Response Body Format  .  .  .  .  .  .  .  .  .  .  .  .  . 4-3
Required Response Arguments  .  .  .  .  .  .  .  .  .  .  .  . 4-3
Broker  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-3
Member Name.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-4
Metadata Version Information   .  .  .  .  .  .  .  .  .  . 4-4
User information  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-4
Capability URL List  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-5
Optional Response Arguments  .  .  .  .  .  .  .  .  .  .  .  . 4-5
Accounting Information.  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-5
Access Control Information.  .  .  .  .  .  .  .  .  .  .  .  . 4-5
Office List Information.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-6
Well-Known Names.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-6
Capability URL List  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-6
Reply Codes   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 4-8

5. GetObject Transaction
5-1
Required Client Request Header Fields   .  .  .  .  .  .  .5-1
Optional Client Request Header Fields .  .  .  .  .  .  .  .5-2
Required Request Arguments  .  .  .  .  .  .  .  .  .  .  .  .  .5-2
Optional Request Arguments   .  .  .  .  .  .  .  .  .  .  .  .  .5-3
Location  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .5-3
Required Server Response Header Fields  .  .  .  .  .  .5-3
Optional Server Response Header Fields   .  .  .  .  .  .5-4
Location  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .5-4
Description  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .5-4
Required Response Arguments   .  .  .  .  .  .  .  .  .  .  .  .5-4
Optional Response Arguments.  .  .  .  .  .  .  .  .  .  .  .  .5-5
Metadata   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .5-5
Resources  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .5-5
Multipart Responses  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .5-5
General Construction.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .5-5
Error Handling  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .5-6
Reply Codes .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .5-7

6. Logout Transaction
6-1
Required Request Arguments  .  .  .  .  .  .  .  .  .  .  .  .  .6-1
Optional Request Arguments   .  .  .  .  .  .  .  .  .  .  .  .  .6-1
Required Response Arguments   .  .  .  .  .  .  .  .  .  .  .  .6-1
Optional Response Arguments.  .  .  .  .  .  .  .  .  .  .  .  .6-1
Logout Response Body Format   .  .  .  .  .  .  .  .  .  .  .  .6-2
Reply Codes .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .6-2

7. Search Transaction
7-1
Search Types  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-1
Search Terminology.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-2
Field Delimiter  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-2
Field Name  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-2
Record Count .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-2
Other terms .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-2
Required Request Arguments  .  .  .  .  .  .  .  .  .  .  .  .  .7-2
Search Type and Class  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-2
Query Specification .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-3
Optional Request Arguments   .  .  .  .  .  .  .  .  .  .  .  .  .7-3
Count .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-3
Format  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-3
Limit   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-4
Offset .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-4
Select .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-5
Restricted Indicator .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-5
StandardNames  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-6
Required Response Arguments   .  .  .  .  .  .  .  .  .  .  .  .7-6
Search Response Body Format .  .  .  .  .  .  .  .  .  .  .  .  .7-6
Query language  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-8
Query language BNF  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .7-8
Query parameter interpretation .  .  .  .  .  .  .  .  .  .7-9
Sub-queries .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 7-10
Reply Codes .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 7-11

8. Get Transaction
8-1
Required Request Arguments  .  .  .  .  .  .  .  .  .  .  .  .  .8-1
Optional Request Arguments   .  .  .  .  .  .  .  .  .  .  .  .  .8-1

Version 1.7.2

  Real Estate Transaction Specification   i

Required Response Arguments  .  .  .  .  .  .  .  .  .  .  .  . 8-1
Optional Response Arguments  .  .  .  .  .  .  .  .  .  .  .  . 8-1
Status Conditions .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 8-1

Validation Expression.  .  .  .  .  .  .  .  .  .  .  .  .  .  . 11-25
Validation External  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 11-27
Validation External Type .  .  .  .  .  .  .  .  .  .  .  .  . 11-28

9. Change Password Transaction
9-1
Required Request Arguments  .  .  .  .  .  .  .  .  .  .  .  .  . 9-1
Optional Request Arguments  .  .  .  .  .  .  .  .  .  .  .  .  . 9-1
Required Response Arguments  .  .  .  .  .  .  .  .  .  .  .  . 9-1
Optional Response Arguments  .  .  .  .  .  .  .  .  .  .  .  . 9-1
Reply Codes   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 9-2
Encryption Key Construction   .  .  .  .  .  .  .  .  .  .  .  .  . 9-2
ECB Padding  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 9-2
Effect of change .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 9-2

10. Update Transaction
10-1
Required Request Arguments  .  .  .  .  .  .  .  .  .  .  .  .  10-1
Optional Request Arguments  .  .  .  .  .  .  .  .  .  .  .  .  10-2
Required Response Arguments  .  .  .  .  .  .  .  .  .  .  .  10-2
Optional Response Arguments  .  .  .  .  .  .  .  .  .  .  .  10-2
Update Response Body Format  .  .  .  .  .  .  .  .  .  .  .  10-2
Error block  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  10-3
Warning block .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  10-4
Validation .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  10-4
Lookup   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  10-5
MultiSelect Lookup.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  10-5
Range  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  10-5
Test Expression  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  10-5
External .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  10-5
Reply Codes   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  10-5

11. Metadata Format
11-1
Organization and Retrieval .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-1
Metadata Organization  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-1
General Rules for Interpretation   .  .  .  .  .  .  .  .  11-1
Metadata Retrieval Hierarchy  .  .  .  .  .  .  .  .  .  .  11-3
Metadata Format .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-3
System-Level Metadata  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-4
System.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-4
Resources .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-5
Resource Metadata Content .  .  .  .  .  .  .  .  .  11-6
Foreign Keys .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-8
ForeignKeys Metadata Content .  .  .  .  .  .  .  11-9
Metadata Format for Class Elements .  .  .  .  .  .  .  11-10
Class  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-10
Table .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-11
Update.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-16
Update Type  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-16
Metadata Format for Shared Elements   .  .  .  .  .  11-18
Object  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-18
Lookup   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-19
Lookup Type .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-19
Search Help.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-20
Edit Mask  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-21
RETS Regular Expression Specification  .  11-22
Update Help  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-22
Validation Lookup   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-23
Validation Lookup Type .  .  .  .  .  .  .  .  .  .  .  .  .  11-24

12. GetMetadata Transaction
12-1
Required Client Request Header Fields.  .  .  .  .  .  . 12-1
Required Request Arguments   .  .  .  .  .  .  .  .  .  .  .  . 12-1
Optional Request Arguments.  .  .  .  .  .  .  .  .  .  .  .  . 12-1
Required Server Response Header Fields  .  .  .  .  . 12-2
Required Response Arguments   .  .  .  .  .  .  .  .  .  .  . 12-2
Optional Response Arguments.  .  .  .  .  .  .  .  .  .  .  . 12-2
Metadata Response Body Format .  .  .  .  .  .  .  .  .  . 12-2
Reply Codes .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 12-3

13. Compact Data Format
13-1
Overall format   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 13-1
Decoded Format .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 13-1
Multivalued Fields .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 13-2
Transmission standards   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 13-2

14. Session Protocol
14-1
Connection Establishment   .  .  .  .  .  .  .  .  .  .  .  .  .  . 14-1
Authorization .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 14-1
Session .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 14-2
Termination .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 14-2

15. [deprecated] ServerInformation 
15-1
Transaction
Required Request Arguments   .  .  .  .  .  .  .  .  .  .  .  . 15-1
Optional Request Arguments.  .  .  .  .  .  .  .  .  .  .  .  . 15-1
Response Format   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 15-1
Well-known names  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 15-2
Reply Codes .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 15-3

16. Acknowledgments

17. Authors

18. References

DTD References

16-1

17-1

18-1

A-1

Sample COMPACT Metadata Responses B-1
System  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   B-1
Resource .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   B-1
Foreign Keys   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   B-2
Class.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   B-2
Table   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   B-3
Update  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   B-3
Update Type   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   B-3
Object   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   B-4
Lookup .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   B-4
Lookup Type   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   B-4

ii  Real Estate Transaction Specification

Version 1.7.2

Search Help.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . B-5
Edit Mask  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . B-5
Update Help  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . B-5
Validation Lookup   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . B-6
Validation Lookup Type .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . B-6
Validation Expression  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . B-6
Validation External .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . B-7
Validation External Type   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . B-7

Summary of RETS Reply Codes

C-1

Maximum Field Length and Display 
D-1
Information
Datatype Boolean .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   D-1
Datatype Character .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   D-2
Datatype Decimal  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   D-2

Document Revision History

E-1

Index of Compliance Items

1-1

Index

Index-1

Version 1.7.2

  Real Estate Transaction Specification   iii

iv  Real Estate Transaction Specification

Version 1.7.2

List of Figures

11.1  Metadata Structure .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  11-2

Version 1.7.2

  Real Estate Transaction Specification   v

vi  Real Estate Transaction Specification

Version 1.7.2

List of Tables

3-1  General Status Codes.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 3-8
4-1  Well-Known Names for Input Fields   .  .  .  .  .  . 4-6
4-2  Capability URL Descriptions  .  .  .  .  .  .  .  .  .  .  . 4-7
4-3  Valid Reply Codes for Login Transaction   .  .  . 4-8
5-1  GetObject Reply Codes   .  .  .  .  .  .  .  .  .  .  .  .  .  . 5-7
6-1  Logout Reply Codes   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 6-2
7-1  Search Transaction Reply Codes.  .  .  .  .  .  .  .  7-11
9-1  Change Password Reply Codes   .  .  .  .  .  .  .  .  . 9-2
10-1  Update Transaction Reply Codes   .  .  .  .  .  .  10-5
11-1  MetadataSystem Compact Header Attributes11-5
11-2  System Compact Header Attributes   .  .  .  .  11-5
11-3  Metadata: System Field   .  .  .  .  .  .  .  .  .  .  .  .  11-5
11-4  Well-Known Resource Names   .  .  .  .  .  .  .  .  11-5
11-5  Resource Metadata Compact Header Attributes11-6
11-6  Metadata: Resource Description Fields   .  .  11-6
11-7  ForeignKeys Metadata Compact Header Attributes

11-9

11-8  Metadata Content: Foreign Keys.  .  .  .  .  .  .  11-9
11-9  Class Metadata Compact Header Attributes11-10
11-10  Metadata Content: Resource Class   .  .  .  11-11
11-11  Table Metadata Compact Header Attributes11-12
11-12  Metadata Content - Tables.  .  .  .  .  .  .  .  .  11-12
11-13  Update Metadata Compact Header Attributes11-16
11-14  Metadata Content – Update.  .  .  .  .  .  .  .  11-16
11-15  UpdateType Metadata Compact Header Attributes

11-17

11-16  Metadata Content – Update Type  .  .  .  .  11-17
11-17  Well-known Object Types  .  .  .  .  .  .  .  .  .  11-18
11-18  Object Metadata Compact Header Attributes11-18
11-19  Metadata Content: Resource Object   .  .  11-18
11-20  Lookup Metadata Compact Header Attributes11-19
11-21  Metadata Content: Lookup  .  .  .  .  .  .  .  .  11-19
11-22  Lookup Type Metadata Compact Header Attributes

11-20

11-23  Metadata Content: Lookup Type.  .  .  .  .  11-20

11-24  Search Help Metadata Compact Header Attributes

11-21

11-25  Metadata Content: Search Help.  .  .  .  .  . 11-21
11-26  EditMask Metadata Compact Header Attributes

11-21

11-27  Metadata Content: Edit Mask  .  .  .  .  .  .  . 11-21
11-28  RETS Regular Expression Metacharacters11-22
11-29  Update Help Metadata Compact Header Attributes

11-23

11-30  Metadata Content: Update Help  .  .  .  .  . 11-23
11-31  ValidationLookup Metadata Compact Header 
Attributes.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 11-23
11-32  Metadata Content: Validation Lookup   . 11-23
11-33  Validation Lookup Type Metadata Compact Header 
Attributes.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 11-24

11-34  Metadata Content: Validation Lookup Type11-24
11-35  Validation Expression Types .  .  .  .  .  .  .  . 11-25
11-36  Validation Expression Operators .  .  .  .  . 11-26
11-37  Validation Expression Special Operand Tokens

11-26

11-38  Validation Expression Metadata Compact Header 
Attributes.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 11-27
11-39  Metadata Content: Validation Expression11-27
11-40  Validation External Metadata Compact Header 
Attributes.  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 11-28
11-41  Metadata Content: Validation External . 11-28
11-42  Validation External Type Metadata Compact 

Header Attributes   .  .  .  .  .  .  .  .  .  .  .  .  .  .  . 11-29
11-43  Metadata Content: Validation External Type11-29
12-1  GetMetadata Reply Codes .  .  .  .  .  .  .  .  .  .  . 12-3
13-1  Compact Data Field Format Representation13-2
15-1  Well-Known Parameter Names .  .  .  .  .  .  .  . 15-2
15-2  ServerInformation Reply Codes   .  .  .  .  .  .  . 15-3
A-1  DTD References   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .   A-1
C-1  Consolidated list of RETS reply codes.  .  .  .  .  .C-1

Version 1.7.2

  Real Estate Transaction Specification   vii

viii  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0INTRODUCTION

1.1 Purpose

1.2 Scope

The Real Estate Transaction Standard (RETS) is a specification for a standard 
communication method between computer systems exchanging real estate information. It 
defines a standard interface for use by applications such as agent desktop software, IDX 
(Internet Data Exchange) systems, data aggregation systems, and many other systems that 
store, display or operate on real estate listing, sales and other data.

This specification describes the Real Estate Transaction Standard communication 
protocol. Together with the companion XML DTDs (Document Type Definitions) listed 
in Appendix A, it constitutes the specification for the standard. 

This specification is intended to define only the minimum a product or service must do in 
order to be considered “compliant”. This specification is extensible and nothing in the 
specification precludes a vendor from adding data or functionality over and above that 
detailed here. However, when a function is provided or a data element is stored by a 
compliant system, it must offer access to the function or mechanism in a way that 
complies with the specification in order to be considered compliant. 

1.3 Requirements

1.3.1 Required Features

This specification uses the same words as RFC 1123 [1] for defining the significance of 
each particular requirement. These words are:

MUST

This word or the adjective "required" means that the item is an 
absolute requirement of the specification. A feature that the 
specification states MUST be implemented is required in an 
implementation in order to be considered compliant.

Version 1.7.2

  1-1

SHOULD

MAY

This word or the adjective “recommended” means that there may 
exist valid reasons in particular circumstances to ignore this item, 
but the full implications should be understood and the case 
carefully weighed before choosing a different course. A feature 
that the specification states SHOULD be implemented is treated 
for compliance purposes as a feature that may be implemented.

This word or the adjective “optional” means that this item is truly 
optional. A feature that the specification states MAY be 
implemented need not be implemented in order to be considered 
compliant. However, if it is implemented, the feature MUST be 
implemented in accordance with the specification.

An implementation is not compliant if it fails to satisfy one or more of the MUST 
requirements for the protocols it implements. An implementation that satisfies all the 
MUST and all the SHOULD requirements for its protocols is said to be “unconditionally 
compliant”; one that satisfies all the MUST requirements but not all the SHOULD 
requirements for its protocols is said to be “conditionally compliant.” 

Client and server implementations should generally follow the Internet protocol 
convention of being strict in what they generate, but tolerant in what they accept. 
However, in cases where tolerance of deviations from the specification could result in an 
incorrect interpretation of user data or intentions, implementers are urged to reject 
transactions rather than supplying possibly-incorrect defaults.

1.3.2 Compatibility with Prior Versions

The RETS 1.7.2 specification supersedes previous versions of the RETS specification. 
There is no requirement for a client or server that advertises itself as “compliant with RETS 
1.7.2” to interoperate with earlier versions. However, client and server implementers are 
urged to support the prior versions, RETS 1.7 and RETS 1.5, in order to insure a smooth 
transition.

1.4 Terminology

Arguments

Class

Client

Endpoint

Metadata

Tag/value pairs passed to a transaction as part of the Argument-
List.

A subset of data elements within a Resource that share common 
metadata elements.

The system requesting data. This may well be a server seeking to 
update itself from another server. The specification does not 
assume any particular kind of client.

Either a server or client.

The set of data that describes data fields in detail.

Metadata Dictionary

The set of data that describes the available metadata. It is used to 
determine the different classes of accessible data on the server and 
does not describe the fields within the those classes. It also defines 

1-2  Real Estate Transaction Specification

Version 1.7.2

Object

Optional

Required

Resource

what different types of searches are available (tax, open house, 
etc.).

For purposes of RETS and its GetObject transaction, a collection 
of octets treated as a unit and associated with a unique resource 
element.

A field or feature described by this specification but not required 
for an endpoint to be considered compliant. The specification 
states the action to be taken by a compliant system in the absence 
of an optional field. The fact that the specification designates a 
field as optional does not mean that the recipient of a transaction 
that is missing optional fields is required to provide all services 
that could be required if the field were present.

A compliant server or client MUST include any field designated 
as required. A transaction that does not include every required 
field MUST be rejected by the recipient.

A collection of data having the external appearance of belonging 
to a single database and being accessible for search or update via 
RETS transactions.

Resource Element

An individual record from a resource identified by a Resource 
Key.

Resource Key

The unique key that identifies a resource element.

Server

Request ID

The system providing data (also referred to as the “host”).

A client-provided character string of up to 64 printable characters 
which uniquely identifies a request to a client. The contents are 
implementation-defined. Defined in Section 3.4, “Optional Client 
Request Header Fields”.

StandardName

The name of a data field as it is known in the Real Estate 
Transaction Standard Data Dictionary.

SystemName

The name of a data field as it is known in the metadata.

Version 1.7.2

  1-3

1-4  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0NOTATIONAL CONVENTIONS

2.1 Augmented BNF

This document expresses message layouts and character sequences in an augmented 
Backus-Naur Form (BNF) similar to that used by RFC 2822 [4] and defined in RFC 2234 
[22].

2.2 Typographic Conventions

Parsing constructs and examples are set in a monospaced font:

Server: Microsoft-IIS/4.0

In parsing constructs, textual elements that are required exactly as shown are indicated by 
boldface type., while textual elements that represent placeholders for actual data are 
indicated by a slanted font:

Server: server identifier

Entities designated by a textual definition contain that definition enclosed in angle 
brackets:

<any 8-bit sequence of data>

Atoms and primitive entities are indicated by ITALIC CAPS:

1*64ALPHANUM

Two nonprinting characters also have significance in some RETS constructs. These may be 
represented by special printing graphics:

→

⋅

Tab character, ASCII HT, an octet with a value of 09

Space character, ASCII SP, an octet with a value of 32. The symbol is used 
where needed for clarity.

Certain features of the standard may be superseded as the standard develops. These 
features should be avoided and are indicated by the text [deprecated] which will follow the 
first use of the feature terminology. Future releases of the standard may remove 
deprecated features.

Version 1.7.2

  2-1

2.3 Rules

The following rules are used throughout this specification to describe basic parsing 
constructs. The US-ASCII coded character set is defined by ANSI X3.4-1986 [5].

Parsed entities are constructed combinations of atoms or other entities as defined below. 
Atoms may be combined and repeated to form longer constructs. When there are 
constraints on the repetition of atoms, the constraints are expressed by a notation of the 
form:

m * n

where both m and n are integers. m represents the minimum allowed number of 
repetitions, and n represents the maximum. If m is omitted, it is presumed to be zero; if n 
is omitted, it is presumed to be infinite. For example, the syntactic construct

1*64ALPHANUM

means a string of ALPHANUMs containing at least 1 and at most 64.

When a parsing construct is represented by a string of entities, some of which are optional, 
the optional entities are enclosed in square brackets. For example, in the string

error-number [error-code]

the error-number entity is required, while the error-code entity is optional.

Elements separated by the vertical bar are alternatives. The entity description

ALPHA | DIGIT

means “either an ALPHA or a DIGIT”.

2.4 Atoms and Primitive Entities

Note The definitions for ALPHA, CHAR, CTL, DIGIT , HEXDIG and OCTET are derived from RFC 

2234.

ALPHA

CHAR

CTL

DIGIT

HEXDIG

OCTET

::= %x41-5A | %x61-7A

; A-Z | a-z

::=  %x01-7F

; ANY 7-BIT US-ASCII CHARACTER,

; EXCLUDING NUL

::= %x00-1F | %x7F

; controls

::=  %x30-39

; 0-9

::=  DIGIT | "A" | "B" | "C" | "D" | "E" | "F"

::= %x00-FF

; any 8-bit sequence of data

2-2  Real Estate Transaction Specification

Version 1.7.2

BOOLEAN

TRUE

FALSE

RETSID

RETSNAME

IDALPHANUM

ALPHANUM

::= TRUE | FALSE

::= “1”

::= “0”

::= 1*32ALPHANUM

::= 1*64IDALPHANUM

::= ALPHANUM | “_”

::= ALPHA | DIGIT

SQLFIELDNAME

::= ALPHA *31ALPHANUM <except ANSI SQL 92 reserved words>

CR

LF

SP

HT

<"> or "

NULL

CRLF or ↵

LWS

HEX

LHEX

::= <US-ASCII CR, carriage return (13)>

::= <US-ASCII LF, linefeed (10)>

::= <US-ASCII SP, space (32)>

::= <US-ASCII HT, horizontal-tab (9)> 

::= <US-ASCII double-quote mark (34)>

::= <no character>

::= CR LF

::= [CRLF] 1*( SP | HT )

::= "A" | "B" | "C" | "D" | "E" | "F" | "a" | "b" | "c" 

| "d" | "e" | "f" | DIGIT

::= "a" | "b" | "c" | "d" | "e" | "f" | DIGIT

OPTNONNEGATIVENUM ::= NULL | NONNEGATIVENUM

; null or >= 0

OPTPOSITIVENUM

::= NULL | POSITIVENUM

; null or >= 1

NONNEGATIVENUM

::=  “0” | POSITIVENUM

; also known as cardinal numbers or counting numbers

; consisting of integers greater than 0

NONZERODIGIT

::=  %x31-39

; 1-9

PLAINTEXT

::= <any OCTET except CTLs>

POSITIVENUM

::= NONZERODIGIT *DIGIT

; > 0

SERIAL

::= “-1” | NONNEGATIVENUM

; 

Version 1.7.2

  2-3

TEXT

::= <any OCTET except CTLs, but including LWS>

Note

Implementers are cautioned that the definition of the TEXT atom may conflict with 
certain outputs, in particular a collision between the delimiter octet of Section 7.2.1 and 
the output information when using the formats COMPACT or COMPACT-DECODED. 
Further, the definition may conflict with escaping rules for well-formed XML responses. 
The responsibility for resolving these conflicts lies with the transmitting party. In 
particular, the responses to Search, Update and GetMetadata may have this conflict.

TOKENCHAR

TOKEN

TSPECIALS

::= <any CHAR except CTLs or TSPECIALS>

::= 1*TOKENCHAR

::= "(" | ")" | "<" | ">" | "@" | "," | ";" | ":" | "\" 
| <"> | "/" | "[" | "]" | "?" | "=" | "{" | "}" | SP 
| HT

QUOTED-STRING

::= ( <"> *(QDTEXT) <"> ) 

QDTEXT

::= <any TEXT except <">>

RETSDATETIME

::= date-time | partial-date-time

RETSTIME

DATE

::= full-time | partial-time

::= Date using the format defined in RFC 2616 as rfc1123-date.

Note The definitions for the date and time are derived from RFC 3339.

date-fullyear

::= 4DIGIT

date-month

::= 2DIGIT ; 01 - 12 

date-mday

time-hour

time-minute

time-second

::= 2DIGIT ; 01 - 28, 01-29, 01-30, 01-31, based on month/year

::= 2DIGIT ; 00 - 23

::= 2DIGIT ; 00 - 59

::= 2DIGIT ; 00 - 58, 00 - 59, 00 - 60 based on leap second rules

time-secfrac

::= “.”1DIGIT

time-numoffset

::= (“+”|”-”) time-hour “:” time-minute

time-offset

::= “Z” | time-numoffset

partial-time

::= time-hour “:” time-minute ”:” time-second [time-

full-date

full-time

date-time

secfrac]

::= date-fullyear “-” date-month “-” date-mday

::= partial-time time-offset

::= full-date “T” full-time

partial-date-time ::= full-date “T” partial-time

Note

ISO 8601, RFC 3339 and the W3C note provide for additional constraints to the formats. 
Based on common usage patterns, this standard applies the following additional 
constraints to improve interoperability and compatibility. The representation of the time 
offset UTC character ‘Z’ and the date-time separator character ‘T’ MUST be upper case. 

2-4  Real Estate Transaction Specification

Version 1.7.2

The time-secfrac is limited to one digit only. The date and time representations are 
intended for machine processing, therefore, no whitespace is expected in any of the atoms. 
Examples of the format are similar to that of the W3C note, for example, 1997-07-
16T19:20:30.4+01:00 or 1997-07-16T18:20:30.4Z. Servers and Clients MUST treat the 
time-offset ‘Z’ and “+00:00” as identical times. Servers and Clients MAY use the 
interpretation of RFC 3339 section 4.3 Unknown Local Offset Convention where the time-
offset “-00:00” is semantically different from “+00:00” and represents a known UTC time 
but unknown local time.

URI

hier-part

::= scheme “:” hier-part [ “?” query ] [ “#” fragment]

::= "//" authority path-abempty

| path-absolute

| path-rootless

|path-empty

scheme

authority

userinfo

host

port

IP-literal

IPvFuture

::= ALPHA *( ALPHA |DIGIT |"+" |"-" |"." )

::= [ userinfo "@" ] host [ ":" port ]

::= *( unreserved |pct-encoded |sub-delims |":" )

::= IP-literal |IPv4address |reg-name

::= *DIGIT

::= "[" ( IPv6address |IPvFuture  ) "]"

::= "v" 1*HEXDIG "." 1*( unreserved |sub-delims |":" )

IPv6address

::= 6( h16 ":" ) ls32

|"::" 5( h16 ":" ) ls32

|[               h16 ] "::" 4( h16 ":" ) ls32

|[ *1( h16 ":" ) h16 ] "::" 3( h16 ":" ) ls32

|[ *2( h16 ":" ) h16 ] "::" 2( h16 ":" ) ls32

|[ *3( h16 ":" ) h16 ] "::"    h16 ":"   ls32

|[ *4( h16 ":" ) h16 ] "::"              ls32

|[ *5( h16 ":" ) h16 ] "::"              h16

|[ *6( h16 ":" ) h16 ] "::"

::= 1*4HEXDIG

::= ( h16 ":" h16 ) / IPv4address

::= dec-octet "." dec-octet "." dec-octet "." dec-octet

::= DIGIT                 ; 0-9

|%x31-39 DIGIT         ; 10-99

|"1" 2DIGIT            ; 100-199

|"2" %x30-34 DIGIT     ; 200-249

h16

ls32

IPv4address

dec-octet

Version 1.7.2

  2-5

reg-name

path

|"25" %x30-35          ; 250-255

::= *( unreserved / pct-encoded / sub-delims )

::= path-abempty    ; begins with "/" or is empty

| path-absolute   ; begins with "/" but not "//"

|path-noscheme   ; begins with a non-colon segment

|path-rootless   ; begins with a segment

|path-empty      ; zero characters

path-abempty

::= *( "/" segment )

path-absolute

:= "/" [ segment-nz *( "/" segment ) ]

path-noscheme

::= segment-nz-nc *( "/" segment )

path-rootless

path-empty

segment

segment-nz

::= segment-nz *( "/" segment )

::= 0<pchar>

::= *pchar

::= 1*pchar

segment-nz-nc

::= 1*( unreserved |pct-encoded |sub-delims |"@" )

pchar

query

fragment

pct-encoded

unreserved

reserved

gen-delims

sub-delims

; non-zero-length segment without any colon ":"

::= unreserved |pct-encoded |sub-delims |":" |"@"

::= *( pchar |"/" |"?" )

::= *( pchar |"/" |"?" )

::= "%" HEXDIG HEXDIG

::= ALPHA |DIGIT |"-" |"." |"_" |"~"

::= gen-delims |sub-delims

::= ":" |"/" |"?" |"#" |"[" |"]" |"@"

::= "!" |"$" |"&" |"'" |"(" / ")"| "*" |"+" |","| ";" |"="

Note The definition for URI is derived from RFC 3986.

2-6  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0MESSAGE FORMAT

RETS uses HTTP version 1.1 [2] for sending messages between clients and servers. It 
defines three additional HTTP headers, and some RETS transactions constrain the values 
of certain headers defined by HTTP 1.1 and/or make certain headers designated as 
optional in HTTP 1.1 mandatory when used for RETS. In addition, RETS requests use 
HTML 4.01 [16] form encoding to encapsulate request parameters. In addition, a 
compliant RETS client MUST implement cookie handling as specified in RFC 2109 [15].

The information below summarizes some of the requirements of HTTP 1.1 and 
HTML 4.01 for ease of reference. However, in all cases, the underlying standards are the 
normative references for message formats.

3.1 General Message Format

3.1.1 RETS HTTP/1.1 Encapsulation

RETS messages are encapsulated as the bodies of HTTP/1.1 requests and responses. The 
request body may be null, depending on the request. The response body is never null

Note that, per RFC 2822, keywords in header key-value pairs are not case-sensitive. The 
values, however, may be case-sensitive depending on context.

3.1.2 Request Arguments

RETS requests are HTML 4.01-compliant form submissions, following all of the 
specifications in the HTML 4.01 recommendation. Note that the HTML 4.01 specification 
provides that:

• Key names in key/value pairs are not case-sensitive.

• Both key names and key values MUST be encoded as specified in HTML 4.01 section 

17.13.4, with + characters replacing spaces, and then reserved characters being 
escaped per RFC 2396 [13], unless the client uses a content-type of multipart/form-
data.

Version 1.7.2

  3-1

3.1.3 Response Bodies

The body of a response to most RETS requests is a well-formed XML document; the 
exceptions are the Get transaction (section 8) and the GetObject transaction (section 5). 
This means that servers must construct the body in accordance with the XML specification 
[17], and that clients must parse the body in accordance with that specification.

3.2 Request Format

A RETS request is either an HTTP GET request or an HTTP POST request. In the case of 
the GET-request the Argument-List is appended to the Request-URI after a delimiting 
question mark (“?”). For the post-request the Argument-List is sent as the first entity body 
for the POST method.

get-request

::=GET⋅Request-URI [ ? Argument-List] ⋅HTTP-Version CRLF 

*message-header 
CRLF

post-request ::=POST⋅Request-URI ⋅ HTTP-VersionCRLF 
*message-header 
CRLF 
[Argument-List]

The Request-URI, HTTP-Version and message-header are defined in RFC 2616. The 
detailed construction of the Argument-List is defined in HTML 4.01.

3.3 Required Client Request Header Fields

The HTTP header of any messages sent from the client MUST contain the following 
header fields:

User-Agent

This header field contains information about the user agent 
originating the request. This is for statistical purposes, the tracing 
of protocol violations, and automated recognition of user agents 
for the sake of tailoring responses to avoid particular user agent 
limitations, as well as providing enhanced capabilities to some 
user-agents. All client requests MUST include this field. This is a 
standard HTTP header field as defined in RFC 2616.

User-Agent

::= User-Agent:   1* product

product

::= TOKEN [/ product-version]

product-version::= TOKEN

Example:

User-Agent: CMAZilla/4.00

Product tokens should be short and to the point: use of them for advertising or other non-
essential information is explicitly forbidden. Although any token character may appear in 
a product-version, this token SHOULD only be used for a version identifier (i.e., 
successive versions of the same product SHOULD only differ in the product-version 
portion of the product value). For more information about User-Agent see RFC 2616.

3-2  Real Estate Transaction Specification

Version 1.7.2

A server MAY advertise additional capabilities based on the client application User-Agent, 
and MAY refuse to proceed with the authorization if an acceptable User-Agent has not 
been supplied. A server MAY also choose to authenticate the client application identity 
cryptographically using the RETS-UA-Authorization header; see section 3.4 for 
additional information.

RETS-Version

Cookie

The client MUST send the RETS-Version. The convention used is 
a “<major>.<minor>.<release>” numbering scheme similar to the 
HTTP Version in Section 3.1 of RFC 2616. The version of a RETS 
message is indicated by a RETS-Version field in the header of the 
message.

The client MUST implement cookie handling as specified in 
RFC 2109. If any server response has included a valid Set-Cookie 
header, and the cookie in that header has not expired, the client 
MUST return the corresponding Cookie header. See RFC 2109 
for the full specification.

3.4 Optional Client Request Header Fields

Authorization

RETS-Request-ID

Authorization header field as defined in RFC 2617. See 4.1, 
“Security”, as well as RFC 2617, for additional information.

A character string of printable characters which the client can use 
to identify this request. The contents are implementation-
defined. If this field is included in a request from the client then 
the server MUST return it in the response.

RETS-Request-ID::= 1*64ALPHANUM

Accept-Encoding

A comma-separated list of MIME types indicating the content 
encoding schemes that the client is willing to accept. This is 
intended to support the use of compression in data returns; see 
section 3.8 for additional information. 

Accept-Encoding::= 1*64ALPHANUM/1*64ALPHANUM *[,1*64ALPHANUM/

1*64ALPHANUM…]

RETS-UA-Authorization A client MAY support authentication of its User-Agent value 
by including the RETS-UA-Authorization header. Servers MAY 
require this header with a valid value before providing services.

RETS-UA-Authorization::= ua-method ua-digest-response

ua-method::=

Digest

ua-digest-response::="*LHEX "

See section 3.10 for the method of computing the ua-digest-
response value.

The client MAY send this header under any circumstances. It 
need not send this header if the server has not indicated that it 

Version 1.7.2

  3-3

requires user-agent authentication by responding to a transaction 
with a RETS error code of 20037.

In addition to the header fields listed here, the client may send any header compliant with 
HTTP 1.1.

3.5 Response Format

The general server response to a request is either a well-formed XML document returning 
RETS-encapsulated data or error information, or, for the Get transaction and for 
successful GetObject transactions, the content of the requested object in the format given 
in the response’s HTTP Content-Type header. Note that this is an ordinary HTTP 
response per RFC 2616.

The more common HTTP Status-Codes are provided in Section 3.9, though any status 
code defined in RFC 2616 is permissible. Servers MUST use appropriate predefined status 
codes when communicating with the client.

The Status-Code is intended to provide HTTP level errors to the client (Authorization, 
URI, etc.). Software level errors (search queries, invalid argument values, etc.) should be 
returned in the reply-code. If the server is unable to determine that a particular request is 
in fact a RETS request, it MUST return an HTTP status code indicating the type of error. 

Except in those transactions specifically stating otherwise, a RETS response body is a well-
formed XML document with the following general form:

response-body ::= RETS-response

RETS-response ::=body-start-line 

response 

[rets-status] 
body-end-line ]

body-start-line::= <RETS 1*SP ReplyCode= quoted-reply-code 1*SP 

ReplyText= quoted-string *SP> 

response

::= {key-value-body | data} 

key-value-body ::= <RETS-RESPONSE>CRLF 
*(key = value CRLF) 

</RETS-RESPONSE>

rets-status

::= <RETS-STATUS [1*SP ReplyCode=quoted-end-reply-code 

1*SP ReplyText=quoted-string *SP]/> 

The rets-status MAY be included in the response if the ReplyCode or ReplyText given 
in the body-start-line becomes invalid during the creation of the response. If the server 
includes a rets-status in its reply, the client MUST use the ReplyCode and ReplyText 
from the rets-status rather than from the body-start-line.

body-end-line ::= </RETS> 

If a body-start-line is returned in the response then the body-end-line MUST also be 
returned.

3-4  Real Estate Transaction Specification

Version 1.7.2

quoted-reply-code::=<">1*5DIGITS<">

The reply-code is included to provide a mechanism to pass additional information to the 
client in the event that the request is processed OK (Status-Code = 200) but some 
condition still exist that may require an action by the client. A value of '0' indicates success. 
Applicable reply-codes can be found under specific transactions.

quoted-end-reply-code::= <">1*5DIGITS<">

The end-reply-code is included to provide a mechanism to pass additional information 
to the client in the event that the request being processed by the server errors before the 
request has been completed. This allows the server to start streaming out data before it has 
completed processing the request. A value of 0 indicates success, however the server 
SHOULD only send an end-reply-code if there is an error.

The valid <key>, <value> and <data> elements are defined in the Response Arguments 
section for each transaction.

RETS 1.7.2 requires all server responses to be well-formed XML, In addition, this specification requires that 
clients parse RETS responses as XML, not as simple text streams. The response formats shown here are 
normative with respect to content, but not normative with respect to form. That is, servers are free to produce 
response XML in any format that complies with the W3C XML 1.0 recommendation. XML escaping of 
content is implied, as is XML processing of line endings and whitespace. See the W3C XML Recommendation 
1.0, Third Edition, for full information on XML.

An example server-reply where the reply body consists of key-value pairs:

NOTE

HTTP/1.1 200 OK 
Server: Microsoft-IIS/4.0 
Date: Sun, 20 Mar 2005 12:03:38 GMT 
Content-Type: text/xml 
Cache-Control: private 
RETS-Version: RETS/1.7.2

<RETS ReplyCode="0" ReplyText="SUCCESS"> 
<RETS-RESPONSE> 
Key1=Value1 
Key2=Value2 
</RETS-RESPONSE> 
</RETS>

3.6 Required Server Response Header Fields

The HTTP header of any messages sent from the server MUST contain the following 
header fields:

Date

The server MUST send the date using the format defined in RFC 
2616 using format rfc1123-date.

Example: 

Date: Sun, 20 Mar 2005 12:03:38 GMT

As defined by rfc1123-date, the Date MUST be represented in 
Greenwich Mean Time (GMT), without exception.

Cache-Control

The RFC 2616 standard general-header field is used to specify 
directives that MUST be obeyed by all caching mechanisms along 

Version 1.7.2

  3-5

the request/response chain. The directives specify behavior 
intended to prevent caches from adversely interfering with the 
request or response. This field SHOULD be set to "private" for all 
transaction in this specification.

Example:

Cache-Control: private

Content-Type

This is a standard HTTP header field as defined in RFC 2616. It 
specifies the media type of the underlying data. The server MUST 
return this field in all replies. For most replies this will be set to 
"text/xml". See Section 5.5 in the GetObject Transaction for 
exceptions and more information on this field.

Example: 

Content-Type: text/xml

RETS-Version

The server MUST send the RETS-Version. The convention used 
is a “<major>.<minor>.<revision>” numbering scheme similar to 
the HTTP Version in Section 3.1 of RFC 2616. The version of a 
RETS message is indicated by a RETS-Version field in header of 
the message.

RETS-Version ::= "RETS-Version:" version-info

version-info ::= "RETS/" 1*DIGIT "." 1*DIGIT "." 1*DIGIT

Example: 

RETS-Version: RETS/1.7.2

Applications sending request or response messages, as defined by this specification, 
MUST include a RETS-Version of "RETS/1.7.2". Use of this version number indicates 
that the sending application is compliant with this specification.

3.7 Optional Server Response Header Fields

Content-Length

Transfer-Encoding

Content-Encoding

The Content-Length entity-header field indicates the size of the 
message-body, in decimal number of octets. This is a standard 
header field defined in RFC 2616 and is required for all requests 
containing a message-body not using Chunked transfer encoding.

The Transfer-Encoding entity-header field when set to the 
Chunked value, indicates the size of the message-body is in the 
chunk stream. This is a standard header field defined in RFC 2616 
and is required for all responses with a body not using Content-
Length or a Content-Type: Multipart response.

The Content Encoding entity-header field MAY be returned by 
the server if the client has included an AcceptEncoding header in 
its request () indicating that it can accept one or more 
compression types supported by the server. It is recommended 
that servers accept at least application/gzip (see 3.8, “Data 
Compression in RETS Transactions”).

Content-Encoding::= 1*64ALPHANUM / 1*64ALPHANUM

3-6  Real Estate Transaction Specification

Version 1.7.2

RETS-Request-ID

The contents of the RETS-Request-ID header, if any, sent by the 
client in the request. If a RETS-Request-ID is included in a 
request from the client then the server MUST return it in the 
response.

RETS-Request-ID::= 1*64ALPHANUM

Server

The server standard response-header field contains information 
about the software used to handle the request. The format of this 
field specified in RFC 2616 Section 3.8.

Example:

Server: Microsoft-IIS/4.0

RETS-Server

The RETS server vendor and server-controlled version number. 
This is not necessarily the same as the Server response-header 
field; it will be different if the HTTP server is separate from the 
RETS server. The format of this field is specified in RFC 2616 
Section 3.8.

Example:

RETS-Server: AcmeRETS/1.0

Set-Cookie

The server MAY use HTTP cookies to maintain state 
information. See RFC 2109 for the format of the Set-Cookie 
header.

A cookie having a name of RETS-Session-ID defines the RETS 
session ID, which is used in calculating the RETS User-Agent 
Authentication (section 3.10). Cookies with other names have no 
special meaning in RETS but MAY be used when necessary.

In addition to the header fields listed here, the server may send any header compliant with 
HTTP 1.1.

3.8 Data Compression in RETS Transactions

Clients and servers may choose to support data compression in data returned from the 
server. To indicate its willingness to accept compressed data, a client includes an 
Accept-Encoding header in its request. If the server supports one of the compression 
methods accepted by the client, it can include a Content-Encoding header in its response 
indicating the compression method it has chose.

Clients and servers choosing to implement compression SHOULD at least support GZip 
compression. This method is implemented by freely-available source code in a number of 
languages, as well as in several proprietary software development environments. A second 
freely-available alternative is BZIP. Clients and servers are free to choose other encoding 
methods as well.

Version 1.7.2

  3-7

3.9 General Status Codes

Any of the following status codes (in addition to the others provided in RFC 2616) may be 
returned by a server in response to any request:

Table 3-1 General Status Codes

Status

Meaning

200
400

401

402

403

404

405

406

408

411

412

413

414

500

501

503

505

Operation successful.
Bad Request 
The request could not be understood by the server due to malformed syntax.
Not Authorized 
Either the header did not contain an acceptable Authorization or the username/
password was invalid.  The server response MUST include a WWW-
Authenticate header field.
Payment Required 
The requested transaction requires a payment which could not be authorized.
Forbidden 
The server understood the request, but is refusing to fulfill it.
Not Found 
The server has not found anything matching the Request-URI.
Method Not Allowed 
The method specified in the Request-Line is not allowed for the resource 
identified by the Request-URI.
Not Acceptable 
The resource identified by the request is only capable of generating response 
entities which have content characteristics not acceptable according to the accept 
headers sent in the request.
Request Timeout 
The client did not produce a request within the time that the server was prepared 
to wait.
Length Required 
The server refuses to accept the request without a defined Content-Length.
Precondition Failed 
Transaction not permitted at this point in the session
Request Entity Too Large 
The server is refusing to process a request because the request entity is larger than 
the server is willing or able to process.
Request-URI Too Long 
The server is refusing to service the request because the Request-URI is longer 
than the server is willing to interpret. This error usually only occurs for a GET 
method.
Internal server error. 
The server encountered an unexpected condition which prevented it from 
fulfilling the request.
Not Implemented 
The server does not support the functionality required to fulfill the request.
Service Unavailable 
The server is currently unable to handle the request due to a temporary 
overloading or maintenance of the server.
HTTP Version Not Supported 
The server does not support, or refuses to support, the HTTP protocol version 
that was used in the request message.

3-8  Real Estate Transaction Specification

Version 1.7.2

HTTP error status returns are only to be used for system level, transport syntax, and 
invalid transaction errors. RETS error status codes are used to indicate errors in the 
request arguments or the transaction processing. 

3.10 Computing the RETS-UA-Authorization Value

The RETS User Agent Authorization digest response value is used in the RETS-UA-
Authorization header specified in section 3.4. It is computed as follows:

a1

::= MD5( product : UserAgent-Password )

ua-digest-response::= HEX( MD5( HEX(a1): RETS-Request-ID : session-id : 

version-info))

where:

product

The first product value taken from the User-Agent header 
(section 3.3). Note that the product value consists of both the 
product token and version.

UserAgent-Password::=TOKEN

This value is a secret shared between the client and server.

RETS-Request-ID ::= RETS-Request-ID

session-id

This value MUST be the same as that sent with the RETS-
Request-ID header. If the client does not use the RETS-
Request-ID header, this token is empty in the calculation.

::= If the server has sent a Set-Cookie header with a cookie name 
of RETS-Session-ID, session-id is the value of that cookie. If 
the server has not sent a cookie with that name, or if the cookie 
by that name has expired, this token is empty in the 
calculation.

version-info

::= The value of the RETS-Version header sent by the client with 

this transaction.

Each individual value in the concatenated string is included with whitespace removed 
from the beginning and end of that element, that is, there is no whitespace on either side of 
the delimiting colon characters.

The method of performing the MD5 calculation is given in RFC 1321.

Version 1.7.2

  3-9

3-10  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0LOGIN TRANSACTION

A client MUST issue a login request prior to proceeding with any other request. The Login 
transaction verifies all login information provided by the user and begins a RETS session. 
Subsequent session control may be mediated by HTTP cookies or any other method, 
though clients are required to support at least session control via HTTP cookies. Section 
14 describes the session protocol in detail.

The server’s response to the Login transaction contains the information necessary for a 
client to issue other requests. It includes URLs that may be used for other RETS requests, 
and may also contain identity and parameter information if required by the functions 
supported by the server.

4.1 Security

4.1.1 User Authentication

While this specification does not require the use of security — it is permissible, for 
example, to operate a publicly-accessible RETS server — most operators of RETS servers 
will wish to authenticate users. A server that requires that users be authenticated MAY 
implement RFC 2617, HTTP Authentication. The use of at least digest authentication is 
strongly recommended.

4.1.2 Client Authentication

Client authentication may be performed through the use of the optional RETS-UA-
Authorization header (section 3.4). Prior versions of this specification used a specially-
calculated cnonce value in the Authorization header to implement this function. A server 
implementing this version of the RETS specification MUST accept the RETS-UA-
Authorization header for client authentication. It MAY accept RFC 2617-style 
authentication as in prior versions of the RETS specification.

4.1.3 Data Security

Needs for secure HTTP transactions cannot be met by authentication schemes. For those 
needs, HTTP-over-TLS (commonly known as HTTPS) is a more appropriate protocol. A 

Version 1.7.2

  4-1

compliant server MAY support only HTTP-over-SSL. In this case, the server SHOULD 
listen on port 12109 rather than the standard RETS port, 6103. 

4.2 Authorization Example

The following example assumes that a client application is trying to access the Login URI 
on the server using the POST method, and without using client authentication. The URI is 
“http://www.example.com/login”. Both client and server know that the username is 
“joesmith”, and the password is “SuperAgent”. The example also assumes the use of 
authentication using RFC 2617.

The first time the client requests the document, no Authorization header is sent, so the 
server responds with:

HTTP/1.1 401 Unauthorized

WWW-Authenticate: Digest realm="Users@example.com", 

nonce="dcd98b7102dd2f0e8b11d0f600bfb0c0" 
opaque="5ccdef346870ab04ddfe0412367fccba"

The client may prompt the user for the username and password, after which it will 
respond with a new request, including the following Authorization header:

Authorization: Digest username=“joesmith”, 

realm=“Users@example.com”, 
nonce=“dcd98b7102dd2f0e8b11d0f600bfb0c0”, 
opaque=“5ccdef346870ab04ddfe0412367fccba”, 
uri=“/login", 
response=“13258d9b0bc217c9502b47e32dff8ee9”

4.3 Required Request Arguments

There are no required request arguments.

4.4 Optional Request Arguments

4.4.1 BrokerCode Argument

brokerCodeArgument::= BrokerCode = broker-code [, broker-branch ]

Some servers may support the scenario where a user belongs to multiple brokerages. If this 
is the case then the broker information (broker-code and broker-branch) must be input 
during login. If they are not included then the list of broker codes/branches is passed back 
to the client application through the response along with a “20012 Broker Code Required” 
reply-code.

broker-code

::= 1*24ALPHANUM

broker-branch

::= 1*24ALPHANUM

4.4.2 SavedMetadataTimestamp Argument

savedMetadataTimestamp::=SavedMetadataTimestamp = saved-timestamp

4-2  Real Estate Transaction Specification

Version 1.7.2

The client MAY inform the server of the timestamp associated with the version of 
metadata that it has currently saved. The server MAY use this to adapt to an earlier version 
of metadata than it chooses to advertise, or simply log the value to note out-of-date client 
metadata, or ignore the value entirely. In particular, the server is not required to alter its 
behavior in any way based on the value of this argument.

saved-timestamp ::= RETSDATETIME

4.5 Optional Response Header Fields

There are no additional optional response header fields.

4.6 Login Response Body Format

The body of the login response has three basic formats when replying to a request. The 
simplest form is when there is an error:

<RETS 1*SP ReplyCode= quoted-reply-code 1*SP  
ReplyText= quoted-string *SP /> 

The second case is where the user belongs to more than one broker and they have not 
provided broker information as part of the login. The reply contains a list of all brokerages 
the user belongs to.

<RETS ReplyCode = “20012” 1*SP ReplyText = quoted-string *SP >  
<RETS-RESPONSE>CRLF 
2*( Broker = broker-code [ , broker-branch ] CRLF ) 
</RETS-RESPONSE> 
</RETS> 

The third case is the normal “OK” response. In this case several arguments are passed back 
to the client in the response.

<RETS 1*SP ReplyCode= quoted-reply-code 1*SP 

ReplyText= quoted-string *SP > 

<RETS-RESPONSE> 
member-name-key 
user-info-key 
broker-key 
metadata-ver-key 
metadata-timestamp-key 
min-metadata-timestamp-key 
[ office-list-key ] 
[ balance-key ] 
[ timeout-key ] 
[ pwd-expire-key ] 
capability-url-list
</RETS-RESPONSE> 
[<RETS-STATUS [1*SP ReplyCode= quoted-end-reply-code 1*SP ReplyText= 
quoted-string *SP]/> 
</RETS> CRLF 

4.7 Required Response Arguments

4.7.1 Broker

broker-key

::= Broker = broker-code [, broker-branch] CRLF

Broker information for the logged in user is returned to the client.

Version 1.7.2

  4-3

broker-code

::= 1*24ALPHANUM

broker-branch

::= 1*24ALPHANUM

These parameters are used in the validation routines of the Update transaction (see 
Section 10 for more information).

4.7.2 Member Name

member-name-key ::= MemberName = member-name CRLF

The member's full name (display name) as it is to appear on any printed output, for 
example “Jane T. Row”.

member-name 

::= 1*48TEXT

4.7.3 Metadata Version Information

The metadata version and timestamp keys indicate the current and minimum-acceptable 
versions of metadata.

metadata-ver-key ::= MetadataVersion = metadata-version CRLF

This is the most current version of the metadata that is available on the server. 

metadata-version

::=1*2DIGITS . 1*2DIGITS [. 1*5DIGITS]

It uses a “<major>.<minor>.<release>” numbering scheme. The version is advisory and is 
not used by the metadata currency scheme.

metadata-timestamp-key::= MetadataTimestamp = RETSDATETIME CRLF

This is the timestamp associated with the current version of metadata on the host. If the 
client has cached an earlier version of metadata, it SHOULD take whatever action is 
necessary to load the current version of metadata. 

min-metadata-timestamp-key::= MinMetadataTimestamp = RETSDATETIME CRLF

This is the earliest version of the metadata that the host will support. If the version of the 
metadata being used by the client has a timestamp earlier than this time the client 
SHOULD retrieve the newer metadata from the host. In any case, the client MUST NOT 
send transactions using metadata older than MinMetadataTimestamp.

The definition of the minimum version of the metadata is to permit clients to ignore non-
essential changes to components such as help text and user-readable descriptions. 

4.7.4 User information

user-info-key

::= User = user-id , user-level , user-class ,  

agent-code CRLF

This key contains basic information about the user that is stored on the server. If a server 
does not support one of these fields then it MUST set the returned value to empty (a zero-
length string).

user-id

::= 1*30ALPHANUM

user-class

::= 1*30ALPHANUM

4-4  Real Estate Transaction Specification

Version 1.7.2

user-level

::= 1*5DIGIT 

agent-code

::= 1*30ALPHANUM

The agent-code is the code that is stored in the property records for the listing agent, 
selling agent, etc. In some implementations this may be the same as the user-id. The fields 
user-class and user-level are implementation dependent and may not exist on some 
systems, in which case, an empty string should be returned. These parameters are used in 
the validation routines of the Update transaction (see Section 10 for more information).

4.7.5 Capability URL List

capability-url-list::= see Section 4.10 for format information

The server MUST return a capability list that includes at least Search, Login and 
GetMetadata. The server MAY in addition return any of the other types in Section 4.10. If 
the server supports any of the additional functions (and the client is entitled to access the 
function by virtue of the supplied login information), it MUST provide URLs for those 
functions. The server MAY supply URLs in addition to those in Section 4.10 based on the 
user-agent. If it does, it MUST follow the format specified in Section 4.10.

4.8 Optional Response Arguments

4.8.1 Accounting Information

balance-key

::= Balance = balance CRLF

If the server supports an active billing account then this value SHOULD represent a user-
readable indication of the money balance in the account.

balance

::=  1*32ALPHANUM

4.8.2 Access Control Information

timeout-key

::= TimeoutSeconds = 1*5DIGIT CRLF

The number of seconds after a transaction that a session will remain alive, after which the 
server will terminate the session automatically (e.g. invalidate the session-id). This is 
commonly referred to as the inactivity timeout. A server need not provide this capability; 
however, if it does use session timeouts in order to prevent monopolization of resources, it 
MUST inform the client of the timeout interval by returning this response field.

pwd-expire-key

::= Expr = pwd-expire-date , pwd-expire-warn CRLF

pwd-expire-date ::= RETSDATETIME

pwd-expire-warn ::= [“-”]1*3DIGIT

The pwd-expire-key indicates when a user password will expire. The pwd-expire-date is 
the date that the current user password becomes invalid. The pwd-expire-warn is the 
number of days before the expiration date that the user should be warned of the upcoming 

Version 1.7.2

  4-5

password expiration. A pwd-expire-warn value of “-1” indicates that the password 
expiration is disabled.

4.8.3 Office List Information

office-list-key ::= OfficeList = broker-code [; broker-branch ] 

*( , broker-code [; broker-branch ]) CRLF

If the logged in user is a company owner or manager they may have rights to login to 
multiple offices. The office-list-key is an enumeration of the offices to which the server 
will permit login.

broker-code

::=  1*24ALPHANUM

broker-branch

::=  1*24ALPHANUM

4.9 Well-Known Names

Some fields returned from the login are considered “Well-Known” and are used in the 
validation routines of the Update transaction. Those fields are as follows:

Table 4-1 Well-Known Names for Input Fields

Well-Known name

Input Return Field

.USERID.
.USERCLASS.
.USERLEVEL.
.AGENTCODE.
.BROKERCODE.
.BROKERBRANCH.

user-id
user-class
user-level
agent-code
broker-code
broker-branch

The client MUST assume a blank value for any well-known name for which the server 
does not supply an input field.

These values are used in Table 11-37, “Validation Expression Special Operand Tokens”.

4.10 Capability URL List

The capability-url-list is the set of functions or URLs to which the login grants access. A 
capability consists of a key and a URL. The list returned from the server in the login reply 
has the following format:

[Action = action-URL CRLF] 
[ChangePassword = change-password-URL CRLF] 
[GetObject = get-object-URL CRLF] 
Login = login-URL CRLF 
[LoginComplete = login-complete-URL CRLF] 
[Logout = logout-URL CRLF] 
Search = search-URL CRLF 
GetMetadata = get-metadata-URL CRLF 

4-6  Real Estate Transaction Specification

Version 1.7.2

[ServerInformation = server-information-URL CRLF] 
[Update = update-URL CRLF]

Table 4-2 Capability URL Descriptions

Parameter

action-URL

change-password-URL
get-metadata-URL
get-object-URL
login-URL

login-complete-URL
logout-URL
search-URL
update-URL
server-information-URL

Purpose

A URL on which the client MUST perform a GET immediately after 
login. This might include a bulletin or the notification of email. The 
client application SHOULD provide a means for the user to view 
the retrieved document. A server is not required to supply an 
Action URL.
A URL for the ChangePassword transaction.
A URL for the Get Metadata transaction.
A URL for the Get Object transaction.
A URL for the Login Transaction. The client software should use 
this URL the next time it performs a Login. If this URL is different 
from the one currently stored by the client the client, MUST update 
the stored one to the new one. This provides a mechanism to move 
the Login server.
RESERVED
A URL for the Logout transaction.
A URL for the Search transaction.
A URL for the Update transaction.
A URL for the System Information transaction

The URLs in the capability-url-list may be specified in any order. Since the list is returned 
in the body, servers MAY include whitespace between the parameter, equals sign and URL. 
Clients SHOULD be prepared to receive the capability-url-list either with or without 
whitespace in the response. The format of each URL follows the pattern defined in the URL 
atom. In addition, the table is extensible; servers may define additional transactions for 
clients to access. If a transaction is offered only to particular user agents, the keys for those 
additional transactions MUST begin with the user-agent token, followed by a dash “-”, 
followed by an implementation-defined function name. Note that this definition does not 
permit spaces in the additional-transaction definition per the ABNF rules.

additional-transaction ::= (“X” | user-agent-token ) “-” function-name CRLF

user-agent-token

::= <token portion of the User-Agent (Section 3.3)>

function-name

::= 1*ALPHA

Example:

Example:

MLSWindows-special = /special_function

X-Delete = http://www.example.com:6103/deletemyrecord

A compliant client need not recognize any transaction that is not included in this 
specification. If some extended transactions are offered to any user-agent, the keys for 
those transactions MUST begin with an “X” followed by a dash, followed by an 
implementation-defined function name. Server implementers who implement potentially-
unrestricted extension transactions are urged to register their keys and service descriptions 
on the RETS web site to encourage wider adoption.

URLs other than the Login URL may be relative URLs. The Login URL MUST be an 
absolute URL. If a URL is not absolute, the client application should canonicalize it 
according to the rules in RFC 2396, section 5. The “base URL” (as defined in RFC 2396, 

Version 1.7.2

  4-7

section 5.1.1) for this operation is the URL used for the current login transaction, not the 
new Login URL.

URLs MUST be URL-encoded per RFC 2396.

4.11 Reply Codes

Table 4-3 Valid Reply Codes for Login Transaction  

Reply Code

Meaning

0
20003

20004 thru 20011
20012

Note: RETS does 
not require that 
a server 
maintain user 
accounts.

20013

20014 thru 20019
20022

20036

20037

20041

20050

Operation successful
Zero Balance
The user has zero balance left in their account.
RESERVED
Broker Code Required 
The user belongs to multiple broker codes and one must be supplied as part of 
the login. The broker list is sent back to the client as part of the login response 
(see section 4.6).
Broker Code Invalid 
The Broker Code sent by the client is not valid or not valid for the user
RESERVED
Additional login not permitted 
There is already a user logged in with this user name, and this server does not 
permit multiple logins.
Miscellaneous server login error 
The quoted-string of the body-start-line contains text that SHOULD be 
displayed to the user
User-agent authentication failed. 
The server requires the use of user-agent authentication (section 4.1.2), and 
the client either did not supply the correct user-agent password or did not 
properly compute its challenge response value.
User-agent authentication required. 
The server requires the use of user-agent authentication (section 4.1.2), and 
the client did not supply the user-agent header values.
Server Temporarily Disabled 
The server is temporarily offline. The user should try again later

4-8  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0GETOBJECT TRANSACTION

The GetObject transaction is used to retrieve structured information related to known 
system entities. It can be used to retrieve multimedia files and other key-related 
information. Objects requested and returned from this transaction are requested and 
returned as MIME media types. The message body for successful retrievals contains only 
the objects in the specified MIME media type. Error responses follow the normal response 
format (section 3.9).

5.1 Required Client Request Header Fields

In addition to the Required Client Request Header Fields specified in Section 3.3, the 
header of any messages sent from the client MUST contain the following header fields:

Accept

The client MUST request a media type using the standard HTTP 
Accept header field. Media-type formats (subtypes) are registered 
with the Internet Assigned Number Authority (IANA) and use a 
format outlined in RFC 2045 [8]. When submitting a request the 
client MUST specify the desired type and format. If the server is 
unable to provide the desired format it SHOULD return a “406 
Not Acceptable” status. However, if there are no objects of any 
subtype available for the requested object the server SHOULD 
return “404 Not Found.” The format of the Accept field is as 
follows:

Accept 

::=  Accept: type / subtype [ ; parameter ] 
*( , SP type / subtype [ ; parameter ])

type 

::=  * | <a publicly-defined type>

subtype 

::= * | <A publicly-defined extension token that 

has been registered with IANA>

parameter

::=  q = < qvalue scale from 0 to 1 >

A complete list of media types and subtypes is available at:

http://www.iana.org/assignments/media-types/

Version 1.7.2

  5-1

The qvalue is used to specify the desirability of a given media type/subtype, with “1” being 
the most desirable, “0” being the least desirable, and a range in between. The default qvalue 
is “1”.

Example:

Accept: image/jpeg, image/tiff;q=0.5,  

image/gif;q=0.1

Verbally, this would be interpreted as “image/jpeg is the preferred media type, but if that 
does not exist, then send the image/tiff entity, and if that does not exist, send the image/gif 
entity.”

The types supported by the server are defined in the Metadata Dictionary as defined in 
section 11.4.1.

5.2 Optional Client Request Header Fields

The GetObject transaction has no optional request header fields.

5.3 Required Request Arguments

Resource

A resource defined in the metadata dictionary (see Section 11.2.2)

The resource from which the object should be retrieved is specified by this entry. For more 
information see 5.9. The resource MUST be a resource defined in the metadata (section 
11.4.1).

Type

The object type as defined in the metadata (see Section 11.4.1)

The grouping category to which the object belongs. Type MUST be an ObjectType 
defined in the Object metadata for this Resource. For more information see section 11.4.1.

ID

ID

A string identifying the object or objects being requested:

::=  resource-set *( , resource-set)

resource-set ::= resource-entity [ : object-id-list ] 

resource-entity ::= 1*ALPHANUM

object-id-list  ::= * | object-id *( : object-id)

object-id 

::= 1*5DIGIT

For objects, the resource-entity is a value (e.g., MLS number, AgentID) from the 
KeyField of the Resource for which the object is to be retrieved.   

The object-id is the particular object to be retrieved. Objects are assumed to be stored 
sequentially on the host beginning with an object-id of “1”. If the object-id is 0 (zero or 
not provided), the designated preferred object of the given type is returned. If the object-id 
is set to “*” then all objects corresponding to the resource-entity are returned. This 
parameter can be used to specify the photo number, e.g. a value of “3” would indicate 
photo number 3. 

If multiple resource-entity or object-id values are sent, or if any object-id-list is “*”, 
then the host MUST respond with a multipart MIME [8] response. See 5.11, “Multipart 
Responses”, for more detail.

5-2  Real Estate Transaction Specification

Version 1.7.2

5.4 Optional Request Arguments

5.4.1 Location

Location

0| 1

This parameter indicates whether the object or a URL to the object should be returned. 
This is used to provide access to the semi-permanent storage location of information for 
access outside of the transaction (e.g. for use in email to a customer). The lifetime of this 
semi-permanent storage is not defined by this specification.

If Location is set to “1” the server MAY return a URL to the given object. The default is 
“0”. The server MAY support this functionality (Location=1) but MUST support 
Location=0. In other words, some servers may store the objects in a database or generate 
them dynamically. Therefore, it may not be possible for those servers to return a URL to 
the requested object. In these cases the server MAY choose not to support Location=1. 
However, all servers MUST support a method to get the object and therefore, MUST 
support the case where Location=0.

When the Location=1, the message body SHOULD contain a  RETS response as described 
in Section 3.5.

5.5 Required Server Response Header Fields

In addition to the other Required Server Header Fields specified in Section 3.6 the 
following response header fields are required.

Content-Type

The media type of the underlying data. The server MUST return 
this field in all replies. Additionally, this field MUST be returned 
as part of the header for each body part. This field MUST be set to 
the type of media returned. See Section 5.1 for more information 
on <type> and <subtype>.

Content-Type ::=  Content-Type: type /subtype

Example: 

Content-Type: image/jpeg

If the client has requested multiple IDs, the server MUST return a multipart message. If it 
does, it MUST return a Content-Type of “multipart/parallel” along with a boundary 
delimiter in the response header. See Section 5.11 for more information on multipart 
responses.

Example:

Content-Type: multipart/parallel; boundary=AAABBBCCC

Content-ID

An ID for the object. This field MUST be returned as part of the 
header for each body part in a multipart response. A value for this 
field MUST be returned for each body part. This value is the 
resource-entity from the GetObject request and MUST match the 
corresponding Resource KeyField value.

Content-ID

::=  Content-ID: 1*128PLAINTEXT

Example:

Content-ID: 123456

Version 1.7.2

  5-3

Object-ID

The object number being returned. This field MUST be returned 
as part of the header for each body part in a multipart response. 
Object-ID::= Object-ID: 1*5DIGIT |“*”

Example: 

Object-ID: 2

Note: The Object-ID may only have the value of “*” in the response when there is an error 
in the response and the request was for all objects using the wildcard request of “*”.

MIME-Version

All responses MUST include a MIME-Version of “1.0” in the 
response header.

Example:

MIME-Version: 1.0

5.6 Optional Server Response Header Fields

In addition to the other Optional Server Header Fields specified in Section 3.7 the 
following response header fields are also optional.

5.6.1 Location

Location

If the client has submitted a request with “Location=1” the 
header of the response MUST contain the Location header field. 
If the server does not support this functionality for a specific 
object, then “Location:” without a URI MUST be returned. If the 
server does not support this functionality for any object, the 
server should return an error type of 20414.

Location

::= Location: URI 

Example:

Location: http://www.example.com/pic/123456.jpg

If the server is returning a multipart response, then this header MUST be included in the 
MIME part headers for each object successfully requested.

5.6.2 Description

Description

A text description of the object.

Description

::= Content-Description: *1024<PLAINTEXT, EXCLUDING CR/

LF>

Example: 

Content-Description: Front View

If the object does not have a description or if the server does not support this feature, the 
header MAY not be returned. If the object has a description and the server is returning a 
multipart response, then this header MUST be included in the MIME part headers for the 
object.

5.7 Required Response Arguments

There are no required response arguments.

5-4  Real Estate Transaction Specification

Version 1.7.2

5.8 Optional Response Arguments

There are no optional response arguments.

5.9 Metadata

To retrieve objects the client MAY first retrieve the metadata that describes the Resources 
and Objects that are available with the GetMetadata transaction described in section 12. A 
full description of the Metadata Dictionary is provided in Section 11.

5.10 Resources

RETS does not require that any particular type of object be made available by a server. 
However, a server MUST use a standard well-known name under which to make its data 
available if a suitable well-known name is defined in the standard.

5.11 Multipart Responses

As described in Section 5.3, in the case where the client has requested multiple resource-
entity or object-id values or if any object-id-list is “*”, the server MUST return a 
multipart response. In the case of multipart responses, in which one or more different sets 
of data are combined in a single body, a “multipart” media type field must appear in the 
entity's header. 

5.11.1 General Construction

RFC 2045 describes the format of an Internet message body containing a MIME message. 
The body contains one or more body parts, each preceded by a boundary delimiter line, 
and the last one followed by a closing boundary delimiter line. After its boundary delimiter 
line, each body part then consists of a header area, a blank line, and a body area.

Example:

HTTP/1.1 200 OK 
Server: Apache/2.0.13 
Date: Fri, 22 OCT 2004 12:03:38 GMT 
Cache-Control: private 
RETS-Version: RETS/1.0 
MIME-Version: 1.0 
Content-type: multipart/parallel; boundary="simple boundary" 

--simple boundary 
Content-Type: image/jpeg 
Content-ID: 123456 
Object-ID: 1 

<binary data> 
--simple boundary 
Content-Type: image/jpeg 
Content-ID: 123457 
Object-ID: 1 

<binary data> 

Version 1.7.2

  5-5

 
 
 
 
--simple boundary-- 

5.11.2 Error Handling

When a client requests multiple objects in a single transaction, one or more of those 
objects may be unavailable. In this case, the server communicates the failure by including a 
RETS return message in place of the unavailable object. In this case, the Content-Type will 
be text/xml, and the content will be a RETS response:

Example:

HTTP/1.1 200 OK 
Server: Apache/2.0.13 
Date: Fri, 22 OCT 2004 12:03:38 GMT 
Cache-Control: private 
RETS-Version: RETS/1.7.2 
MIME-Version: 1.0 
Content-type: multipart/parallel; boundary="simple boundary" 

--simple boundary 
Content-Type: image/jpeg 
Content-ID: 123456 
Object-ID: 1 

<binary data> 
--simple boundary 
Content-Type: text/xml 
Content-ID: 123457 
Object-ID: 1 

<RETS ReplyCode=”20403” ReplyText=”There is no listing with that ListingID”/> 

--simple boundary-- 

If the server is supplying an error message for a wild-card object request (Object-ID of *), 
the Object-ID for the error part SHOULD be * as well.

5-6  Real Estate Transaction Specification

Version 1.7.2

 
 
 
 
5.12 Reply Codes

Table 5-1 GetObject Reply Codes  

Reply Code

Meaning

20400

20401

20402

20403

20406

20407

20408

20409

20410

20411

20412

20413

20414

Invalid Resource 
The request could not be understood due to an unknown resource.
Invalid Type 
The request could not be understood due to an unknown object type for the 
resource.
Invalid Identifier 
The identifier does not match the KeyField of any data in the resource.
No Object Found 
No matching object was found to satisfy the request.
Unsupported MIME type 
The server cannot return the object in any of the requested MIME types.
Unauthorized Retrieval 
The object could not be retrieved because it requests an object to which the 
supplied login does not grant access.
Resource Unavailable 
The requested resource is currently unavailable.
Object Unavailable 
The requested object is currently unavailable.
Request Too Large 
No further objects will be retrieved because a system limit was exceeded.
Timeout 
The request timed out while executing
Too many outstanding requests 
The user has too many outstanding requests and new requests will not be 
accepted at this time.
Miscellaneous error 
The server encountered an internal error.
URL Location Not Supported 
The server does not support retrieving Objects by URL.

Version 1.7.2

  5-7

5-8  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0LOGOUT TRANSACTION

The Logout transaction terminates a session. Except in cases where connection failure 
prevents it or the user has requested an immediate shutdown of the client, the client 
SHOULD send the Logout transaction. If the client sends a Logout transaction, the server 
MUST attempt to send a response before terminating the session.

The server MAY send accounting information back to the client in the response to this 
transaction. The client is not required to display or otherwise process the accounting 
information.

6.1 Required Request Arguments

There are no required request arguments.

6.2 Optional Request Arguments

There are no optional request arguments.

6.3 Required Response Arguments

There are no required response arguments.

6.4 Optional Response Arguments

ConnectTime

The amount of time that the client spent connected to the server, 
specified in seconds.

connect-time ::= ConnectTime=1*9DIGITS CRLF

Billing

If the server supports an active billing account, this is total 
amount billed for this session, specified as TEXT which is 
implementation-defined

billing

::= Billing=*<TEXT, EXCLUDING CR/LF> CRLF

SignOffMessage

Any text. The client MAY display this message, if the server 
includes it in the response. Servers should not expect, however, 

Version 1.7.2

  6-1

that users would read or see the message, since communication 
failure may make it impossible for the client to receive the Logoff 
response.

sign-off-message::= SignOffMessage=*<TEXT, EXCLUDING CR/LF> CRLF

6.5 Logout Response Body Format

The Logout response body is a key/value response (see section 3.5, “Response Format”).

<RETS 1*SP ReplyCode= quoted-reply-code 1*SP 

ReplyText= quoted-string *SP > 

[<RETS-RESPONSE> 
[connect-time] 
[billing] 
[sign-off-message] 
</RETS-RESPONSE>] 
[<RETS-STATUS [1*SP ReplyCode= quoted-end-reply-code 1*SP ReplyText= 
quoted-string *SP]/>] 
</RETS> 

6.6 Reply Codes

Table 6-1Logout Reply Codes

Reply Code

Meaning

0
20701

20702

Operation successful
Not logged in 
The server did not detect an active login for the session in which the Logout 
transaction was submitted.
Miscellaneous error. 
The transaction could not be completed. The ReplyText gives additional 
information.

6-2  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0SEARCH TRANSACTION

The Search transaction requests that the server search one or more searchable databases 
and return the list of qualifying records. The body of the response contains the records 
matching the query, presented in the requested format. The data can be returned in one of 
three formats: COMPACT, COMPACT-DECODED or STANDARD-XML.

7.1 Search Types

Searches are performed on logical groupings of records called Resources. The definition of 
the grouping of records for a specific resource is determined by the server implementation. 
Different server implementations may have different available resources, depending on 
local rules, practises or conditions. Servers may further group the records by Class. 
Different users or different client applications may be provided with different sets of 
Resources and different sets of Classes. A specific value for Resource or Class is referred to 
in this document as a type. For example, a type of Resource is Property using the Standard 
Names definition. Another example may be a type of Resource called Appraisers, being a 
collection of locally licensed real estate property value appraisers. As defined below, a 
server only searches on a single Resource per request. A server MAY provide more than 
one type of Resource in the metadata. The server MUST support searching at least one 
type of resource. The types of resources supported by the server MUST be specified in the 
metadata. Each of the resource searches may by conducted against different databases or 
tables depending on the server implementation. 

Some resources are specified by well-known names. If a server implementation supports 
searches of any of these resources, it MUST use the well-known resource name to identify 
that resource. The list of well-known resource names is provided in Table 11-4, “Well-
Known Resource Names” on page 11-5;s well-known classes for those resources are given 
in Table 11-10, “Metadata Content: Resource Class”.

StandardNames for classes are given in Table 11-10, “Metadata Content: Resource Class”.

Note RETS does not require that a server support any specific resource type or class. The user or 
maintainer of a server is responsible for deciding which resources should be made 
searchable.

Version 1.7.2

  7-1

7.2 Search Terminology

7.2.1 Field Delimiter

A server may designate a particular OCTET to be used as a delimiter for separating entries 
in both the COLUMNS list and the DATA returned using the COMPACT and 
COMPACT-DECODED formats. The octet should be chosen to avoid the need to escape 
data within a record

field-delimiter::= HEX HEX

7.2.2 Field Name

A field is the keyword or code that the server uses to identify a particular column in the 
database table. Each field may be either a System-Name, as defined in the metadata, or a 
Standard-Name, as defined in the Real Estate Transaction XML DTD. The server MUST 
accept either set of names interchangeably.

7.2.3 Record Count

This value indicates the number of records on the server matching the search criteria sent 
in the search query.

record-count ::= 1*9DIGITS

Note that this value may be greater than the number of records returned, if the server has 
limited the size of the return for any reason.

7.2.4 Other terms

XML-data-record::= <A data record as defined by the RETS Data XML DTD>.

7.3 Required Request Arguments

7.3.1 Search Type and Class

The SearchType and Class arguments specify the data that the server is to search.

SearchType

::= ResourceID

The type of search to perform as discussed in Section 7.1 and defined in the Metadata (see 
section 11.2.2).

Class

 :: = 1*32ALPHANUM

This parameter is set to a value that represents the class of data within the SearchType, 
taken from the Class metadata (section 11.3.1). If the resource represented by the 
SearchType has no classes, the Class parameter will be ignored by the server and MAY be 
omitted by the client. If the client does include the Class parameter for a classless search, 
the value SHOULD be the same as the ResourceID in order to insure forward 
compatibility.

7-2  Real Estate Transaction Specification

Version 1.7.2

Note that if StandardNames (Section 7.4.7) is set to 1, then both the SearchType and Class 
are specified using StandardNames.

7.3.2 Query Specification

The specification consists of the query itself together with a designation of the query 
language.

Query

::=  <The query to be executed by the server>

The query is specified in the language described in Section 7.7.

QueryType

::= DMQL2

An enumeration giving the language in which the query is presented. The only valid value 
for RETS 1.7.2 is “DMQL2” which indicates the query language described in Section 7.7

7.4 Optional Request Arguments

7.4.1 Count

7.4.2 Format

The Count argument controls whether the server’s response includes a count.

Count

::=  0 | 1 | 2

If this argument is set to one (“1”), then a record-count is returned in the response in 
addition to the data. Note that on some servers this will cause the search to take longer 
since the count must be returned before any records are received. If this entry is set to two 
(“2”) then only a record-count is returned; no data is returned, but all matches are counted 
regardless of any Offset or Limit parameter. If the Count argument is not present or set 
to zero (“0”) there is no record count returned.

Example:

Count=2

Instructs the server to return only a count of the records matching the query.

The Format argument selects one of the three supported data return formats for the query 
response.

Format

::=  COMPACT | COMPACT-DECODED | STANDARD-XML | 

STANDARD-XML:dtd-version

“COMPACT” means a field list <COLUMNS> followed by a delimited set of the data 
fields <DATA>. “COMPACT-DECODED” is the same as COMPACT except the data for 
any field with an interpretation of Lookup, LookupMulti, LookupBitString or 
LookupBitMask, is returned in a fully-decoded format using the LongValue. See Section 
13 for more information on the COMPACT formats and section 11.4.3 for more 
information on the Lookup types. “STANDARD-XML” means an XML presentation of 
the data in the format defined by the RETS Data XML DTD. Servers MUST support all 
formats. If the format is not specified, the server MUST return STANDARD-XML.

Example:

Format=COMPACT-DECODED

Version 1.7.2

  7-3

7.4.3 Limit

If the client requests STANDARD-XML, it MAY also append a preferred DTD version. 
The server MUST support the current version and SHOULD additionally support at least 
the prior version.

Example:

Format=STANDARD-XML:1.0

The Limit argument requests the server to apply or suspend a limit on the number of 
records returned in the search.

Limit

::=  “NONE” | 1*9DIGIT

In general, the Limit argument operates without consideration of other factors like the 
settings in the system metadata or the fields selected in the Select argument. A special case 
when the Limit=”NONE” is described below.

If this entry is set to a number greater than zero, the server MUST not return more than 
the specified number of records. If the request results in more matches than the server 
returns, the <MAXROWS> tag MUST be sent at the end of the data stream, regardless of 
any Limit parameter specified in the client request.

In general, if this entry is set to (“NONE”) or is not present, the server SHOULD treat this 
as a request to suspend enforcement of any internal download limit. Servers that permit 
the suspension of the limit MUST disable both the <MAXROWS> tag and the return code 
20208, Maximum Records Exceeded when responding to a Limit=”NONE” request. 
Servers that do not permit the suspension of the limit MUST apply the <MAXROWS> and 
return code 20208 in the cases where the query results in more rows than permitted. Client 
implementers should be aware that some server implementations might not honor the 
request to disable the limit or may restrict the request to the selection of certain fields as 
described below; the server operator’s business rules take precedence over the request to 
waive the system download limit.

A server may only support the suspension of the limit for a certain scenario of requests. 
When a server has Classes with a HasKeyIndex value of TRUE in the Class Metadata the 
server MUST suspend enforcement of the download limit for such a Class when the 
Limit=”NONE” and the Select argument contains only field names that have the 
InKeyIndex value of TRUE in the Table Metadata. A server SHOULD support 
HasKeyIndex for each Class and MUST have the InKeyField value of TRUE for at least the 
KeyField of the Class when the HasKeyIndex is TRUE for that Class. A server MAY have 
more than one field with the InKeyField value of TRUE for any Class.

Any request that sets a numeric Limit disables support for unlimited key index results as 
described in section 7.4.5 Select.

7.4.4 Offset

The client may specify that a retrieval start at other than the first record in the set of 
records matching the query by specifying the Offset argument.

Offset

::=  1*9DIGIT

7-4  Real Estate Transaction Specification

Version 1.7.2

This argument indicates to the server that it SHOULD start sending the data to the client 
beginning with the record number indicated, with a value of “1” indicating to start with 
the first record. This can be useful when requesting records in batches, however, client 
implementers should be aware that data on the server MAY change as they iterate through 
the batches and it is possible that some records may be missed or added. In other words, 
the server is not required to maintain a cursor to the data.

Any time an Offset argument is supplied, the resulting data SHOULD be returned in a 
consistent order based on an ordering of the KeyField of the Resource. This ordering 
should be applied to the entire data set and not just the returned data which may be less 
that the total number of records matching the criteria. It is a recommended practice that 
an ascending order be used as the ordering scheme when the KeyField value is a 
sequentially increasing unique identifier, however, servers MAY choose to implement 
some other ordering scheme. This practice will help to ensure subsequent requests will not 
contain duplicate records. Ascending order of the KeyField in this case will also provide 
assurance that newly added records will be more reliably contained in the final Offset 
record set.

Clients iterating over the entire record set on systems that implement this practices MUST 
provide Offset=1 in the first request to assist the server to order the results.

The offset value of ‘0’ is not defined in this standard.

By default, the server MUST return all fields accessible to the client. The client may select a 
subset of those fields by specifying the Select argument.

Select

::=  field *( , field)

This parameter is used to set the fields that are returned by the query. If this entry is not 
present then all allowable fields for the search/class are returned. The server MAY return 
an error when there are unknown fields in the select list. The server MUST NOT return 
more fields than are specified in the Select argument when the client requests COMPACT 
or COMPACT-DECODED data. It MAY return fewer if some of the field names are 
invalid or if a requested field is unavailable to the user based on security or other 
restrictions.

7.4.5 Select

7.4.6 Restricted Indicator

In some instances, the server may withhold the values of selected fields on selected records. 
When business rules withhold the value but the field is returned as part of a response, a 
RestrictedIndicator MUST be used in place of the value.

RestrictedIndicator ::=1*9TOKENCHAR

This entry indicates to the server that it MUST set the restriction indicator to the value 
specified by this tag. The default restricted indicator is a NULL value.

Example:

RestrictedIndicator = ####

Version 1.7.2

  7-5

This would mean that all fields that the user is not allowed to see within a record (e.g. 
ExpirationDate) are returned with a value of ####.

Note that if the client requests fields that the server would withhold for every record, the 
server MAY choose to omit the field from the list returned rather than use the 
RestrictedIndicator for every record.

7.4.7 StandardNames

Queries may use either standard names or system names in the query (Section 7.7). If the 
client chooses to use standard names, it MUST indicate this using the StandardNames 
argument.

StandardNames ::= 0 | 1

If this entry is set to (“0”) or is not present the field names passed in the search are the 
SystemNames, as defined in the metadata. If this entry is set to (“1”) then the 
StandardNames are used for the field names passed in the search. The StandardName 
designation applies to all names used in the query: SearchType, Class, Query and Select.

7.5 Required Response Arguments

There are no required response arguments.

7.6 Search Response Body Format

NOTE

RETS 1.7.2 requires all server responses to be well-formed XML, and additionally requires search transaction 
responses to be valid XML. In addition, RETS requires that clients parse server responses as XML, not as 
simple text streams. The response formats shown here are normative with respect to content, but not 
normative with respect to form. That is, servers are free to produce response XML in any format that 
complies with the W3C XML 1.0 recommendation, so long as it is valid with respect to the appropriate DTD. 
So, for example, when the response format below calls out an empty XML tag, either the abbreviated tag 
format (<MAXROWS/>) or the full format (<MAXROWS></MAXROWS>) may be sent by the server and 
should be interpreted appropriately by the client. In addition, XML escaping of content is implied. See the 
W3C XML Recommendation 1.0, Third Edition, for full information on XML.

The body of the search response has the following format when replying to a request with 
the format set to "COMPACT" or "COMPACT-DECODED":

<RETS 1*SP ReplyCode= quoted-reply-code 1*SP  

ReplyText= quoted-string *SP >  

[ count-tag ] 
[ delimiter-tag ] 
[ column-tag ] 
*( compact-data )  
[ max-row-tag ] 
[<RETS-STATUS [1*SP ReplyCode= quoted-end-reply-code 1*SP 

ReplyText= quoted-string *SP]/>] 

</RETS> CRLF 

The body of the search response has the following format when replying to a format 
request of “STANDARD-XML” data:

7-6  Real Estate Transaction Specification

Version 1.7.2

<?xml version="1.0" ?> 
[doctype] 
<RETS 1*SP ReplyCode= quoted-reply-code 1*SP  

ReplyText= quoted-string *SP > 

[ count-tag ] 
*( XML-data-record ) 
[ max-row-tag ] 
[<RETS-STATUS [1*SP ReplyCode= quoted-end-reply-code 1*SP 

ReplyText= quoted-string *SP]/>] 

</RETS> CRLF 

doctype

::= <!DOCTYPE RETS PUBLIC "-//RETS//DTD RETS XML Search 
Response 1.7.2//EN>" "http://www.rets.org/dtd/2008/

08/RETS-20080829.dtd">

dtd-version

::= <Name of the RETS DTD used to produce this document>

When the client requests the STANDARD-XML representation, it may also specify a DTD 
version. The server MUST support the current version and SHOULD support the previous 
version. Data DTD versions are of the form 

RETS-yyyymmdd.dtd

where yyyymmdd is the release date of the DTD.

compact-data ::=  <DATA> field-delimiter *( field-data field-delimiter 

) </DATA> 

If a “COMPACT” or “COMPACT-DECODED” format is specified in the request then a 
“<DATA>” tag, a delimited list of field-data and a “</DATA>” end tag are returned to the 
client for each record returned. The field-delimiter is determined by the delimiter-tag.

count-tag

::=  <COUNT 1*SP Records="record-count" 1*SP /> 

When the client application specifies that a count should be returned (count-type = "1" | 
"2") a count-tag MUST be sent by the server in the response. The “<COUNT>” tag MUST 
be on the first line following the reply-code line. The record-count value indicates the 
number of records on the server matching the search criteria sent in the search query.

column-tag

::=  <COLUMNS> field-delimiter 1*( field field-delimiter ) 

</COLUMNS> 

If a "COMPACT" or “COMPACT-DECODED” format is specified in the request then a 
“<COLUMNS>” tag, including a delimited list of the names of all the fields of data being 
returned, is sent back in the response. These names are the system-names unless standard-
names were used in the query.

The field-delimiter is determined by the delimiter-tag.

delimiter-tag ::=  <DELIMITER value =" field-delimiter "/> 

This parameter tells the client which character (OCTET) is used as a delimiter for both the 
COLUMNS list and the DATA returned. The server MUST send this parameter for 
“COMPACT” or “COMPACT-DECODED” formats. The “<DELIMITER>” tag MUST 
precede column-tag.

max-row-tag

::= <MAXROWS/> CRLF | 

<MAXROWS></MAXROWS>

Version 1.7.2

  7-7

A tag that indicates the maximum number of records allowed to be returned by the server 
has been exceeded, or alternatively, the Limit number passed by the client in the request 
has been exceeded.

7.7 Query language

The query takes the form indicated below. This is the actual search criteria passed to the 
server. The server parses this query and generates a server-compatible query based on the 
parameters passed in the query-list.

7.7.1 Query language BNF

search-condition::= query-clause | ( search-condition or query-clause)

query-clause ::= boolean-element | ( query-clause and boolean-element )

boolean-element:: = [not] query-element

query-element ::= field-criteria | ( ( search-condition ) )

or

and

not

::= “OR” | “|”

::= “AND” | “,”

::= “NOT” | “~”

field-criteria ::= “(“ field “= “field-value “)“

field-value

::= lookup-list | string-list | range-list | period | number | 

string-literal | “.EMPTY.”

lookup-list

::= lookup-or | lookup-not | lookup-and |“.ANY.”

lookup-or

::= “|“ lookup  *( ,lookup )

lookup-not

::= “~“ lookup *( , lookup )

lookup-and

::= “+“ lookup *( , lookup )

lookup

::= 1*128ALPHANUM | string-literal

string-list

::= string *( , string )

string

::= string-eq | string-start | string-contains | string-char

string-eq

::= 1*ALPHANUM

string-start ::= 1*ALPHANUM *

string-contains::= * 1*ALPHANUM *

string-char

::= *ALPHANUM *(? *ALPHANUM)

string-literal ::= "( PLAINTEXT except " ) *( 2" *( PLAINTEXT except " ) ) "

range-list

::= range *( , range )

range

between

::= between | greater | less

::= ( period | number ) “-“ ( period | number )

7-8  Real Estate Transaction Specification

Version 1.7.2

greater

::= ( period | number | string-eq ) “+”

less

period

number

::= ( period | number | string-eq ) “-”

::= (dmqldate | dmqldatetime | partial-time)

 ::= [-]1*DIGIT ["." *DIGIT]

dmqldate

::= full-date | “TODAY”

dmqldatetime ::= RETSDATETIME | “NOW”

dmqltime

::= partial-time

7.7.2 Query parameter interpretation

Query literal values are interpreted in the value space of the searched field. That is, the data 
type of the searched field determines the interpretation of the search literal values, which 
MUST be valid in that value space.

Dates and times submitted in a query MAY utilize time offsets relative to UTC using the 
dmqldatetime If a dmqldatetime is submitted with time offset information, the server 
system MUST interpret the request using the time offset information. If the time offset is 
not declared in the query, the server system MUST interpret the request using the default 
System time zone offset. This default must match the advertised time zone offset of the 
SYSTEM-METADATA. If no time zone offset is advertised for the server system system, 
the default time zone offset MUST be UTC. The server system MUST interpret the TODAY 
token as the current date and time of the server system. For backward compatibility, the 
server system MUST treat clients with version less than 1.7.2 as submitting dates and times 
using a time zone offset of UTC/GMT. In this case, the advertised time zone offset is 
ignored since the client is not expected to be aware of the time zone offset. The server 
system MUST interpret the token NOW as the current date and time of the server system. 

In processing a literal string, a server MAY substitute a string-char expression (?s) for 
the range of characters that contain any non-ALPHANUM not supported by that server.

In processing decimal numbers, where rounding is necessary, a server SHOULD round 
down for the bottom of ranges or values less than 0.5 and round up for the tops of ranges 
or values 0.5 or greater.

There are four types of field values that can be passed in the query string. They are a 
lookup-list, a range, a string and the special token .EMPTY.. A lookup-list is a field 
that may only contain predefined values, or the special token .ANY., indicating that any 
value is acceptable. “Status” and “Type” are typical examples of fields with a limited range 
of predefined values.

The .ANY. token, if used, is to be interpreted exactly as if it contained all possible values for 
the given field. In particular, the use of .ANY. does not alter any limitation on the number 
of lookup values allowed for the field. It is merely a shorthand method of specifying all 
possible lookup values.

range fields can be searched based on a range of values. “ListPrice” and “ListDate” fall 
into this category. All values specified in a range are to be treated as inclusive (e.g. 2+ is 
the same as 2 or greater, inclusive of 2; 2-3 is the same as 2 to 3, inclusive of 2 and 3; 2- is 

Version 1.7.2

  7-9

the same as 2 or less, inclusive of 2). The types of the range endpoints MUST match the 
data type of the field being searched. In addition, the range-start value MUST be less 
than the range-end value in the value space defined by the searched field, or the result is 
undefined.

A string field is any other character field not falling into the other two categories. These 
are usually freeform text fields. An example of this kind of field is “OwnerName”.

The special value .EMPTY. is to be interpreted as whatever the value of the field would be if 
no value had been entered. Note that this is implementation-defined: it may be the same as 
a search for a null value, or it may be blank or zero. A client should not expect to be able to 
distinguish unentered values from any other values using this search token.

Each field MUST be a SystemName, as defined in the metadata, unless the 
StandardName argument is set to “1”, in which case the field MUST be a StandardName. 
All values submitted for lookup-lists must be the Value in compact format, as defined in 
Section 13.

The data types for field values may be determined by examining the metadata for the 
searched field. In a query using StandardNames, the RETS Data Dictionary gives the 
acceptable data type for search values.

Within range criteria, the datatype of the start and end range values MUST be identical. 
That is, no mixing of datatypes within a specific range is permitted.

If a client submits alookup value containing non-alphanumeric characters, the client 
MUST use the string-literal representation of the Lookup value.

7.7.3 Sub-queries

This query language provides for a nesting of sub-queries. For example:

Query=((AREA=|1,2)|(CITY=ACTON)),(LP=200000+)

Example:

Query=(ST=|ACT,SOLD), 
(LP=200000-350000), 
(STR=RIVER*), 
(STYLE=RANCH), 
(EXT=+WTRFRNT,DOCK), 
(LDATE=2000-03-01+), 
(REM=*FORECLOSE*), 
(TYPE=~CONDO,TWNHME), 
(OWNER=P?LE)

Verbally, this would be interpreted as “return properties with (ST equal ACT or SOLD) and 
(LP between 200000 and 350000, inclusive) and (STR beginning with RIVER) and (STYLE 
equal RANCH) and (EXT equal WTRFRNT and DOCK) and (LDATE greater than or equal to 2000-
03-01) and (REM containing FORECLOSE) and (TYPE not equal to CONDO and not equal to 
TWNHME) and (OWNER starting with P and having LE in the 3rd and 4th characters).”

7-10  Real Estate Transaction Specification

Version 1.7.2

7.8 Reply Codes

Table 7-1

Search Transaction Reply Codes

Reply Code

Meaning

0
20200

20201

20202

20203

20206

20207

20208

20209

20210

20211

20212 [deprecated]

20213[deprecated] 

20514

Operation successful.
Unknown Query Field 
The query could not be understood due to an unknown field name.
No Records Found 
No matching records were found.
Invalid Select 
The Select statement contains field names that are not recognized by the server.
Miscellaneous Search Error 
The quoted-string of the body-start-line contains text that MAY be displayed to 
the user.
Invalid Query Syntax 
The query could not be understood due to a syntax error.
Unauthorized Query 
The query could not be executed because it refers to a field to which the supplied 
login does not grant access.
Maximum Records Exceeded 
Operation successful, but all of the records have not been returned. This reply 
code indicates that the maximum records allowed to be returned by the server 
have been exceeded. Note: reaching/exceeding the "Limit" value in the client 
request is not a cause for the server to generate this error.
Timeout 
The request timed out while executing
Too many outstanding queries 
The user has too many outstanding queries and new queries will not be accepted 
at this time.
Query too complex 
The query is too complex to be processed. For example, the query contains too 
many nesting levels or too many values for a lookup field.
Invalid key request [deprecated] 
The transaction does not meet the server’s requirements for the use of the Key 
option.
Invalid Key[deprecated] 
The transaction uses a key that is incorrect or is no longer valid. Servers are not 
required to detect all possible invalid key values.
Requested DTD version unavailable. 
The client has requested the data in STANDARD-XML format using a DTD 
version that the server cannot provide.

Version 1.7.2

  7-11

7-12  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0GET TRANSACTION

Gets an arbitrary file from the server or performs an arbitrary action, specified by URI. 
This is a standard HTTP GET, per RFC 2616. The file to get is passed as part of the 
Request-URI.

RETS servers need not support the GET transaction to any greater extent than is necessary 
to implement the functionality of the Action URL (see 4.10, “Capability URL List”). If a 
RETS server does not intend to include an Action URL in its login responses, it need not 
support the GET transaction.

8.1 Required Request Arguments

There are no required request arguments.

8.2 Optional Request Arguments

There are no optional request arguments.

8.3 Required Response Arguments

There are no required response arguments.

8.4 Optional Response Arguments

There are no optional response arguments.

8.5 Status Conditions

See the General Status Codes in Section 3.9 for typical Status-Codes.

Version 1.7.2

  8-1

8-2  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0CHANGE PASSWORD TRANSACTION

The Change Password transaction provides a means for the user to change their password. 
The new password is appended to the username and encrypted using the Data Encryption 
Standard (DES), ANSI X3.92, using a hash of the old password as the key.

9.1 Required Request Arguments

PWD

::= PWD= <BASE64(<DES( Password : UserName )>)

This is the Base64 representation of the DES-encrypted UserName and Password.  The 
new Password and the UserName are appended together with a colon (“:”) between and 
the resulting string is encrypted using DES in Electronic Code Book (ECB) mode. The 
DES key is constructed using the procedure in Section 9.6. Base64 encoding is defined in 
RFC 2045 section 6.8.

9.2 Optional Request Arguments

There are no optional request arguments.

9.3 Required Response Arguments

There are no required response arguments.

9.4 Optional Response Arguments

There are no optional response arguments.

Version 1.7.2

  9-1

9.5 Reply Codes

Table 9-1Change Password Reply Codes

Reply Code

Meaning

0
20140

20141

20142

Operation successful.
Insecure password. 
The password does not meet the site’s rules for password security.
Same as Previous Password. 
The new password is the same as the old one.
The encrypted user name was invalid.

9.6 Encryption Key Construction

The new password is communicated to the host as a string encrypted with the Data 
Encryption Standard, ANSI X3.92. DES requires a 64-bit key, which is constructed as 
follows:

1 The old password and username are converted to uppercase and concatenated together.

2 The resulting string is hashed using MD5.

3 The key is taken as the first 64 bits of the resulting hash value. Parity bits must be 

corrected for encoders that check parity.

9.7 ECB Padding

The input to the DES ECB encryption process shall be padded to a multiple of 8 octets in 
the following manner: 

Let n be the length in octets of the input. Pad the input by appending 8 – (n mod 8) octets 
to the end of the input, each having the value 8 – (n mod 8), the number of octets being 
added. In hexadecimal, the possible paddings are 0x01, 0x0202, 0x030303, 0x04040404, 
0x0505050505, 0x060606060606 and 0x07070707070707 and 0x0808080808080808. All 
input is padded with 1 to 8 octets to produce an input string that is a multiple of 8 octets in 
length. The padding can be unambiguously removed after decryption. 

This padding method is compatible with RFC 2315 section 10.3, note 2.

9.8 Effect of change

Servers that return a success status MUST accept the new password and reject the old 
password for all subsequent Login transactions and sessions. Servers that return a success 
status MAY require the use of the new password for all subsequent transactions in the 
current session by issuing a WWW-Authenticate challenge for transactions that do not 
contain the correct credentials. 

If a client fails to receive a response to this transaction, it SHOULD retain both the old and 
new passwords until the effect of the Change Password transaction can be ascertained via a 
successful login. 

9-2  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0UPDATE TRANSACTION

The update transaction is used to modify data on the server. The client transmits 
information describing the update to perform. The information is then validated by the 
server. If there are errors in the data, the server returns an error reply. If there are no 
errors, the record as it was inserted/updated on the server will be returned. The record is 
returned in the same manner as a record is returned from a search.

Update requests MUST use the POST method (rather than the GET method). This allows 
the client to transmit characters beyond the HTTP length limit for the GET method. The 
request MUST use a content-type appropriate to the encoding of the request, per [16]. A 
content-type of text/www-url-formencoded is recommended, but any other method of 
encoding HTML form parameters may be used.

10.1 Required Request Arguments

The request has the following format:

Resource= resource-name 
&ClassName= class-name 
&Validate= validate-flag 
&Type= update-type 
&Delimiter= field-delimiter 
&Record= field-name = field-value *( field-delimiter field-name = 
field-value ) 
[&WarningResponse= warning-response *(field-delimiter warning-
response)]

resource-name ::= 1*32ALPHANUM

The name of the resource to be updated, as specified in the metadata. This is the 
SystemName as defined in Section 11.2.2.

class-name

::= 1*24ALPHANUM

The name of the class to be updated, as defined in the metadata. This is the ClassName as 
defined in section 11.3.1.

validate-flag ::= 0 | 1 | 2

If this parameter is set to one (“1”), then the record is validated by the host. Any fields with 
metadata field “Attributes” set to “Autopop” in the metadata (see Section 11.3.4) will have 
their field values filled in by the server and returned to the client. The record in the server 

Version 1.7.2

  10-1

database is not updated. If this entry is set to zero (“0”) and there are no errors in the 
record the record is updated on the server. If this entry is set to two (“2”), the server 
validates all fields and returns any errors found, but does not store the record.

update-type

::= 1*24 ALPHANUM

The type of update to perform, as specified by the metadata. This is the UpdateType as 
defined in Section 11.3.4.

field-name

::= 1*32ALPHANUM

The name of the field to be updated, as specified in the metadata. This is the SystemName 
as defined in Section 11.3.2.

field-delimiter::= OCTET

The octet which will separate fields in the record. If this is not specified, an ASCII HT 
character is assumed.

field-value

::= <varies depending on the field>

The text representation of the field value as defined by the metadata in Section 11.3.2 
subject to the business rules. The value MUST be submitted as if in COMPACT format.

warning-response::= warning-num = user-response

warning-num

::= 1*5DIGIT

user-response ::= *256TEXT excluding delimiter

The warning-num value is the host warning number that was returned in the prior Update 
Response body. The user-response value is the text of the warning response in response 
to the specified warning. If a warning-num sent in the prior UpdateResponse body had a 
response-required value of 2, then the user-response value MUST NOT be NULL.

10.2 Optional Request Arguments

There are no optional request arguments.

10.3 Required Response Arguments

There are no required response arguments.

10.4 Optional Response Arguments

There are no optional response arguments.

10.5 Update Response Body Format

The body of the update response has the following format when there are no errors:

<RETS 1*SP ReplyCode= quoted-reply-code 1*SP  

ReplyText= quoted-string *SP > CRLF 

transaction-id-tag 
[ delimiter-tag ] 
column-tag 

10-2  Real Estate Transaction Specification

Version 1.7.2

compact-data 
[<RETS-STATUS 1*SP ReplyCode= quoted-end-reply-code 1*SP 
ReplyText= quoted-string *SP/>] 

</RETS> CRLF 

The body of the update response has the following format when there are errors or 
warnings:

<RETS 1*SP ReplyCode= quoted-reply-code 1*SP  

ReplyText= quoted-string *SP > CRLF 

transaction-id-tag 
[ delimiter-tag ] 
column-tag 
compact-data 
[error-block] 
[warning-block] 
</RETS> CRLF 

error-block

= <ERRORBLOCK> CRLF 

1*(<ERRORDATA>→field→error-num→error-
offset→error-text→ 
</ERRORDATA>) 
</ERRORBLOCK> 

warning-block = <WARNINGBLOCK>  

1*(<WARNINGDATA>→field→warning-num→warning-
offset→warning-text→response-required→ 
</WARNINGDATA>) 
</WARNINGBLOCK> 

The format of the <ERRORDATA> and <WARNINGDATA> tag content is identical to 
COMPACT format.

10.5.1 Error block

An Error Block is returned when there is a problem with one or more of the fields. The 
error block contains information about the fields that have errors. It contains the field 
name, an error number, some additional text about the error (error-text), and where in the 
field data the error occurred (error-offset).

error-num

::= 1*5DIGIT

This is the host error number. This number along with the error-text MAY be displayed to 
the user when looking at the corresponding field in the client application.

error-offset ::= 1*5DIGIT

This is the offset into the field data that was sent by the client application to the server. It 
indicates at what character in the field data the problem was encountered. This number is 
set to zero (“0”) if the offset of the error is unknown. 

error-text

::= *64ALPHANUM

This is the error text generated by the host to assist the user in determining the problem 
with the field data. This text is associated with the error-num.

The error return format follows the COMPACT data format in all particulars. This affects 
primarily the quoting of special characters and the selection of the delimiter that separates 

Version 1.7.2

  10-3

the field values. In effect, the error return is a COMPACT data block without the usual 
COLUMNS element.

10.5.2 Warning block

A Warning Block is returned when there is a problem with one or more of the fields that 
would not prevent the record from being saved in the database. It contains a field name, a 
warning number, some additional text about the warning (warning-text), where in the 
field data the warning occurred (warning-offset) and an indicator whether an end-user 
response to this warning is requested or required. The delimiter is the same as the one 
defined for the error-block.

field

::= 1*32ALPHANUM

The SystemName of the field to which the warning applies.

warning-num

::= 1*5DIGIT

The host warning number. This number, along with the warning-text, MAY be 
displayed to an end-user in association with the corresponding field in the client 
application.

warning-text ::= TEXT

warning-offset ::= 1*5DIGIT

The offset into the field data that was sent by the client application to the server. It 
indicates at what character in the field data the problem was encountered. This number is 
set to zero if the offset of the error is unknown or if an offset is inapplicable.

response-required::= 0 | 1 | 2

The response-required value indicates whether an end-user response is requested or 
required:

0 No response is permitted.

1 A response is requested.

2 A response is mandatory.

If the response-required field indicates that a response is mandatory, the client MUST 
send the end-user response for the specific warning-num in the WarningResponse request 
argument in order for this record to be saved to the database.

10.6 Validation

Validation routines are indications of the checks the host system will perform against a 
field value before it is accepted for storage on the host. Some of these routines require data 
available only on the host system. However, others are relatively simple and could be 
performed by any RETS client to prevent invalid field values from being submitted. There 
are several different types of validation to be performed by the client.

A compliant client is not required to enforce the local validations provided in this section. 
However, if a client does not enforce the validations then the likelihood of the server 
rejecting the record is greatly increased.

10-4  Real Estate Transaction Specification

Version 1.7.2

10.6.1 Lookup

The entry is validated against a list of acceptable values. If the metadata described in 
Section 11.3.2 specifies the Interpretation as Lookup the only acceptable values for the field 
are defined in the METADATA-LOOKUP referenced by LookupName. Alternatively, if the 
metadata specifies a ValidationLookup the only acceptable values for the field are defined 
in the METADATA-VALIDATION_LOOKUP referenced by the ValidationLookup field.

10.6.2 MultiSelect Lookup

The entry is validated against a list of acceptable values. If the metadata described in 
Section 11.3.2 specifies the Interpretation as LookupMulti, LookupBitstring or 
LookupBitmask the only acceptable values for the field are defined in the METADATA-
LOOKUP referenced by LookupName. The maximum number of values that can be selected is 
defined by MaxUpdate.

10.6.3 Range

The entry must be between the Minimum and Maximum values specified in the metadata (see 
Section 11.3.2). 

10.6.4 Test Expression

The parameter list contains an expression evaluated by the routine. If the expression is 
true, the value of the field is acceptable. If the expression is false, the value is rejected. See 
Section 11.4.9 for more information on Test Expressions. Test expressions are always 
executed in the order in which they are presented in the metadata.

10.6.5 External

The entry may be validated by searching a server resource. The Resource is defined for 
searching and the parameter list includes a set of suggested input fields, a set of result fields 
to display and a set of result fields to populate into the fields of the resource being updated. 
Information for external validation is provided in Section 11.4.10.

10.7 Reply Codes

Table 10-1 Update Transaction Reply Codes

Reply Code

Meaning

0
20301
20302
20303
20311

20312

Operation successful.
Invalid parameter. Additional information is provided in the error block.
Unable to save record on server.
Miscellaneous Update Error.
WarningResponse was not given for all warnings that contained a 
response-required value of 2.
WarningResponse was given for a warning that contained a response-
required value of 0.

The quoted-string of the body-start-line contains text that MAY be displayed to the user.

Version 1.7.2

  10-5

10-6  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0METADATA FORMAT

Metadata enables a client that receives data from a compliant server to better format the 
data for display, and to store it efficiently for future retrieval. While use of the metadata is 
not necessary to retrieve data for simple display purposes, more sophisticated clients will 
want to use the metadata to make more intelligent use of the information retrieved. 
Metadata MUST be supplied by a compliant server.

11.1 Organization and Retrieval

11.1.1 Metadata Organization

Metadata is organized by table/object, with each table having its own unique set of 
metadata describing the fields available in that table/object. The organization permits 
access to summary or detailed information about one or more resources (see Figure 11.1, 
“Metadata Structure”).

The client retrieves the metadata by using the GetMetadata Transaction specifying the 
METADATA table/object(s) of interest as the Type, and the specific instance in the ID (see 
Section 5). The server supplies the metadata as documents using the formats described in 
this section. The client MUST accept fields and attributes in the metadata that are not 
defined in this standard, although it is not required to process those fields in any way.

The client may cache the metadata between sessions. If it does, it MUST record the value 
of the METADATA-SYSTEM timestamp attribute from each session in which it caches 
retrieved metadata, and MUST request new metadata whenever the MetadataTimestamp 
Login response value changes except when previous versions are permitted by the 
MinMetadataTimestamp value. If a client continues to send transactions using outdated 
metadata, the server’s operation is undefined.

11.1.2 General Rules for Interpretation

In general, metadata keywords defined in this standard such as field names and reserved 
values are not case-sensitive. However, implementers are urged to adopt the strict-
generation/tolerant-acceptance rule and follow the case shown in this standard. 

Version 1.7.2

  11-1

System

Resource

Class

Table

ForeignKey

Object

Update

UpdateType

SearchHelp

EditMask

UpdateHelp

Lookup

LookupType

Validation-
Lookup

Validation-
LookupType

Validation-
External

Validation-
ExternalType

Validation-
Expression

Figure 11.1 Metadata Structure

Servers may choose to extend the content of any metadata table by including additional 
keywords. Metadata field names for such extensions SHOULD begin with the letter “X” 
followed by a hyphen, followed by an implementation-defined token in order to insure 
compatibility with future versions of the standard. 

Clients requesting metadata in COMPACT format MUST ignore any metadata fields 
which they do not understand. In addition, the servers are permitted to send columns in 
any order. The order shown in the examples is not normative.

11-2  Real Estate Transaction Specification

Version 1.7.2

Clients requesting metadata in XML format MUST ignore any <EXTENSION> or 
<PROPRIETARY> elements that they do not understand.

NOTE

RETS 1.7.2 requires all server responses to be well-formed XML, and additionally requires GetMetadata 
responses to be valid XML. In addition, RETS requires that clients parse server responses as XML, not as 
simple text streams. The response formats shown here are normative with respect to content, but not 
normative with respect to form. That is, servers are free to produce response XML in any format that 
complies with the W3C XML 1.0 recommendation, so long as it is valid with respect to the appropriate DTD. 
XML escaping of content is implied, as is XML processing of whitespace and line endings. See the W3C XML 
Recommendation 1.0, Third Edition, for full information on XML.

11.1.3 Metadata Retrieval Hierarchy

The ID argument in the GetMetadata transaction reflects the metadata hierarchy as shown 
in Figure 11.1. For any metadata element, the ID argument is a list of the names of the 
parent elements for the desired element, separated by colons. For example, to retrieve the 
EditMask table for a given named Resource, the argument is simply the ResourceID:

Type: METADATA-EDITMASK 
ID: Property

where Property is the ID of one of the Resources listed in the Metadata-Resource table.

Since Tables are children of Classes, which are in turn children of Properties, the ID 
parameter contains both parents:

Type: METADATA-TABLE 
ID: Property : Res

where Res is a class listed in the Metadata-Class table under the resource Property.

11.1.4 Metadata Format

Compliant RETS servers MUST supply metadata in both formats: COMPACT, described 
below and valid according to the RETS Compact DTD (public identifier -//RETS//DTD 
Compact Metadata 1.7.2//EN), and XML, valid according to the RETS XML Metadata 
DTD (public identifier -//RETS//DTD Metadata Content 1.7.2//EN). See Appendix A 
for system identifiers.

The COMPACT metadata format consists of a sequence of segments with identical 
structure, except for System-level metadata, which has its own structure. The general 
structure for non-System metadata is a tab-delimited table, XML-encapsulated with the 
header record contained within a <COLUMNS> element, and each successive row contained 
within a <DATA> element.:

<METADATA-HEADER header-attributes> 
<COLUMNS>→fieldname *(→fieldname)→</COLUMNS> 
*(<DATA>→fielddata *(→fielddata)→</DATA>) 
</METADATA-HEADER>

METADATA-HEADER is the header name for the segment, given with the description of each 
type of metadata, as are the header-attributes associated with each header. Each 
fieldname is the name of one of the metadata fields given below. Each fielddata value 

Version 1.7.2

  11-3

corresponds to the similarly-positioned fieldname, first to first, second to second and so 
on. 

11.2 System-Level Metadata

11.2.1 System

Clients can determine the number and type of searchable and updateable entities by 
referencing the Resources. A server MUST advertise its resources. It MAY advertise all of 
its available resources or MAY restrict the advertised list by logon or other criteria. A 
server’s advertisement of a resource does not require that the server be able to 
accommodate any arbitrary search for that user; the server MAY restrict access to 
resources that it advertises. If the server supports multimedia objects then it MUST 
advertise the supported types.

All resources that can be searched or updated are defined in the metadata described in this 
section. There are three parts to the metadata. The first part provides system information 
and describes the available resources, the second part describes the class specific metadata 
for a resource, and the third part describes the shared metadata for a resource.

The System metadata starts with a <METADATA-SYSTEM> tag with Version and Date 
attributes. This tag is followed by a <SYSTEM> section, which contains the system 
identification information and time offset. An optional <COMMENTS> section completes the 
System metadata. The System metadata has the following format:

<METADATA-SYSTEM ⋅ Version="system-version" ⋅ Date="system-date" >  
<SYSTEM ⋅ SystemID="code-name" ⋅ SystemDescription="long-name" 
[TimeZoneOffset=”time-zone-offset”]/>  
[ <COMMENTS>  
*( comment ) 
</COMMENTS> ] 

</METADATA-SYSTEM> 

system-version ::= 1*2DIGITS . 1*2DIGITS . 1*5DIGITS

system-date

::= RETSDATETIME

code-name

::= 1*10ALPHANUM

long-name

::= 1*64PLAINTEXT

time-zone-offset::= time-offset

comments

::= TEXT

11-4  Real Estate Transaction Specification

Version 1.7.2

COMPACT header tag: METADATA-SYSTEM

Table 11-1 MetadataSystem Compact Header Attributes  

Attribute

Version

Date

Content

This is the version of the Resource metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.

COMPACT header tag: SYSTEM

Table 11-2 System Compact Header Attributes  

Attribute

SystemId
SystemDescription
TimeZoneOffset

Content

An identifier for the system
An implementation defined description of the system
The Time Zone Offset is the time offset of the server relative to 
UTC. The server MAY provide the TimeZoneOffset to assist in 
correctly calculating date and time values for requests to this 
server. The format is defined in Section 2.4 for the atom time-
offset. Any server that provides the TimeZoneOffset value in 
System Metadata MUST adhere to this value when responding 
to requests. Client applications SHOULD use this value to calcu-
late the correct date and time criteria for requests.

Table 11-3 Metadata: System Field  

Field Name

Content Type

Description

COMMENTS

TEXT

Optional comments about the system. The context 
where the field contains characters may require that 
those characters are escaped by other rules like entity 
encoding.

11.2.2 Resources

RETS does not require that any particular type of data be made available by a server. 
However, a server MUST use a standard well-known name under which to make its data 
available if a suitable well-known name is defined in the standard. Table 11-4 contains the 
list of well-known resource names.

Table 11-4 Well-Known Resource Names 

Resource Name

Purpose

ActiveAgent

A resource that contains information about active agents. These are agents that 
are currently authorized to access the server (paid-up, not retired, etc.)

Agent

History

A resource that contains information about agents. 

A resource that contains information about the accumulated changes to each list-
ing.

Office

A resource that contains information about broker offices.

Version 1.7.2

  11-5

Table 11-4 Well-Known Resource Names (continued)

Resource Name

Purpose

OpenHouse

Property

A resource that contains information about open-house activities.

A resource that contains information about listed properties. Information in this 
resource is described by Real Estate Transaction XML DTD in addition to appro-
priate metadata.

Prospect

A resource that contains information about sales or listing prospects.

Tax

Tour

A resource that contains tax assessor information.

A resource that contains information about tour activities.

Resource Metadata Content

COMPACT header tag: METADATA-RESOURCE

Table 11-5 Resource Metadata Compact Header Attributes  

Attribute

Version

Date

Content

This is the version of the Resource metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.

Table 11-6 Metadata: Resource Description Fields  (Sheet 1 of 3)

Field Name

Content Type

Description

ResourceID

RETSID

The name which acts as a unique ID for this resource.

StandardName

1*64ALPHANUM

The name of the resource. This must be a well-known 
name if applicable.

VisibleName

1*64PLAINTEXT

The user-visible name of the resource.

Description

1*64PLAINTEXT

A user-visible description of the resource.

KeyField

RETSID

ClassCount

POSITIVENUMc

ClassVersion

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

ClassDate

RETSDATETIME

The SystemName (see 11.3.2) of the field that pro-
vides a unique ResourceKey for each element in 
this resource. All classes within a resource must use 
the same KeyField.

The number of classes in this resource. There MUST 
be ClassCount METADATA_CLASS descriptions 
for the resource. There MUST be at least one Class for 
each Resource.

The latest version of the Class metadata for this 
Resource. The convention used is a 
“<major>.<minor>.<release>” numbering scheme. 
The version number is advisory only. 

The date on which the Class metadata for this 
Resource was last changed. Clients MAY rely on this 
date for cache management.

11-6  Real Estate Transaction Specification

Version 1.7.2

Table 11-6 Metadata: Resource Description Fields  (Sheet 2 of 3)

Field Name

Content Type

Description

ObjectVersion

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

ObjectDate

RETSDATETIME

SearchHelpVer-
sion

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

SearchHelpDate RETSDATETIME

EditMaskVer-
sion

11*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

EditMaskDate

RETSDATETIME

LookupVersion

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

LookupDate

RETSDATETIME

UpdateHelpVer-
sion

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

UpdateHelpDate RETSDATETIME

The version of the Object metadata for this Resource. 
The convention used is a 
“<major>.<minor>.<release>” numbering scheme. 
The version number is advisory only. A blank version 
indicates no Object metadata is available for this 
Resource.

The date on which the Object metadata for this 
Resource was last changed. Clients MAY rely on this 
date for cache management. A blank date indicates no 
Object metadata is available for this Resource.

The version of the SearchHelp metadata for this 
Resource. The convention used is a 
“<major>.<minor>.<release>” numbering scheme. 
The version number is advisory only. A blank version 
indicates no SearchHelp is available for this Resource.

The date on which the SearchHelp metadata for this 
Resource was last changed. Clients MAY rely on this 
date for cache management. A blank date indicates no 
SearchHelp is available for this Resource.

The version of the EditMask metadata for this 
Resource. The convention used is a 
“<major>.<minor>.<release>” numbering scheme. 
The version number is advisory only. A blank version 
indicates no EditMask is available for this Resource.

The date on which the EditMask metadata for this 
Resource was last changed. Clients MAY rely on this 
date for cache management. A blank date indicates no 
EditMask is available for this Resource.

The version of the Lookup metadata for this Resource. 
The convention used is a 
“<major>.<minor>.<release>” numbering scheme. 
The version number is advisory only. A blank version 
indicates no Lookup is available for this Resource.

The date on which the Lookup metadata for this 
Resource was last changed. Clients MAY rely on this 
date for cache management. A blank date indicates no 
Lookup is available for this Resource.

The version of the UpdateHelp metadata for this 
Resource. The convention used is a 
“<major>.<minor>.<release>” numbering scheme. 
The version number is advisory only. A blank version 
indicates no UpdateHelp is available for this Resource.

The date on which the UpdateHelp metadata for this 
Resource was last changed. Clients MAY rely on this 
date for cache management. A blank date indicates no 
UpdateHelp is available for this Resource.

Version 1.7.2

  11-7

Table 11-6 Metadata: Resource Description Fields  (Sheet 3 of 3)

Field Name

Content Type

Description

Validation-
ExpressionVers
ion

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

Validation-
ExpressionDate

RETSDATETIME

Validation-
LookupVersion

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

Validation-
LookupDate

RETSDATETIME

ValidationEx-
ternalVersion

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

ValidationEx-
ternalDate

RETSDATETIME

The version of the ValidationExpression metadata for 
this Resource. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. 
The version number is advisory only. A blank version 
indicates no ValidationExpression is available for this 
Resource.

The date on which the ValidationExpression metadata 
for this Resource was last changed. Clients MAY rely 
on this date for cache management. A blank date indi-
cates no ValidationExpression is available for this 
Resource.

The version of the ValidationLookup metadata for 
this Resource. The convention used is a 
“<major>.<minor>.<release>” numbering scheme. 
The version number is advisory only. A blank version 
indicates no ValidationLookup is available for this 
Resource.

The date on which the ValidationLookup metadata 
for this Resource was last changed. Clients MAY rely 
on this date for cache management. A blank date indi-
cates no ValidationLookup is available for this 
Resource.

The version of the ValidationExternal metadata for 
this Resource. The convention used is a 
“<major>.<minor>.<release>” numbering scheme. 
The version number is advisory only. A blank version 
indicates no ValidationExternal is available for this 
Resource.

The date on which the ValidationExternal metadata 
for this Resource was last changed. Clients MAY rely 
on this date for cache management. A blank date indi-
cates no ValidationExternal is available for this 
Resource.

11.2.3 Foreign Keys

The ForeignKeys metadata table allows a server to advertise relationships among its 
offered resources. A RETS client MAY use this information to provide a richer display of 
related information. The ForeignKeys metadata consists of tuples containing a parent 
resource type, a child resource type, and the foreign keys used to traverse the relation.

The nesting of foreign keys MUST be such that recursive searches are NOT REQUIRED to 
obtain data for well-known fields as defined in the RETS DTD. However, nesting of 
foreign keys is allowed except in these cases.

11-8  Real Estate Transaction Specification

Version 1.7.2

ForeignKeys Metadata Content

COMPACT header tag: METADATA-FOREIGN_KEYS

Table 11-7 ForeignKeys Metadata Compact Header Attributes  

Attribute

Version

Date

Content

This is the version of the ForeignKeys metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.

Table 11-8 Metadata Content: Foreign Keys (Sheet 1 of 2)

Metadata Field

Content Type

Description

ForeignKeyID

RETSID

ParentResourceID

RETSID

ParentClassID

RETSID

ParentSystemName

RETSNAME

ChildResourceID

RETSID

ChildClassID

RETSID

ChildSystemName

RETSNAME

A Unique ID that represents the foreign key com-
bination.

The ResourceID (Table 11-6) of the resource 
for which this field functions as a foreign key. The 
name given MUST appear in the METADATA-
RESOURCE table..

The name of the resource class for which this field 
functions as a foreign key. This name MUST 
appear in the RESOURCE-CLASS table for the 
given ParentResourceID.

The SystemName of the field in the given resource 
class that should be searched for the value given in 
the this field. This name must appear as a Sys-
temName in the METADATA-TABLE section of 
the metadata for the ParentClassID, and the 
named item must have its Searchable attribute set 
to TRUE.

The ResourceID (Table 11-6) of the resource 
for which this field functions as a foreign key. The 
name given MUST appear in the METADATA-
RESOURCE table.

The name of the resource class for which this field 
functions as a foreign key. This name MUST 
appear in the RESOURCE-CLASS table for the 
given ChildResourceID.

The SystemName of the field in the given 
resource class that should be searched for the value 
given in this field. This name must appear as a Sys-
temName in the METADATA-TABLE section of 
the metadata for the ChildClassID, and the named 
item must have its Searchable attribute set to 
TRUE.

Version 1.7.2

  11-9

Table 11-8 Metadata Content: Foreign Keys (Sheet 2 of 2)

Metadata Field

Content Type

Description

ConditionalParent-
Field

RETSNAME

ConditionalParent-
Value

RETSNAME

The SystemName of a field in the parent’s 
METADATA-TABLE that should be examined to 
determine whether this parent-child relationship 
should be used. If this is blank, the relationship is 
unconditional. If ConditionalParent-
Field is present and nonblank, then Condi-
tionalParentValue MUST be present and 
nonblank.

The value of the field designated by Condi-
tionalParentField indicating that this rela-
tion should be used. If the type of the field named 
in ConditionalParentField is numeric, 
then this value is converted to numeric type before 
comparison. If the type of the field named in Con-
ditionalParentField is character, then the 
shorter of the two values is padded with blanks and 
the comparison made for equal length. If Condi-
tionalParentField is present and nonblank, 
then ConditionalParentValue MUST be 
present and nonblank.

11.3 Metadata Format for Class Elements

All tables that can be searched are defined in a document with the format defined in this 
section. There are three parts to this section. The first part describes the searchable tables, 
the second part describes the lookups referenced within the table section, and the third 
describes the help text associated with searches and edit masks associated with updates.

11.3.1 Class

A given data resource may contain multiple classes of entries that can be searched or 
updated separately. The metadata for a resource supporting searchable classes MUST 
contain a class description for each class supported.

COMPACT header tag: METADATA-CLASS

Table 11-9 Class Metadata Compact Header Attributes  

Attribute

Version

Date

Resource

Content

This is the version of the Class metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource in which this class resides.

11-10  Real Estate Transaction Specification

Version 1.7.2

Table 11-10 Metadata Content: Resource Class  

Metadata Field

Content Type

Description

ClassName

RETSID

The name which acts as a unique ID for the class. 

StandardName

1*64PLAINTEXT

The XML standard name. This is the name from the 
Real Estate Transaction XML DTD. Examples include 
ResidentialProperty, LotsAndLand, 
CommonInterest,MultiFamily for the 
Resource type of Property.

VisibleName

1*64PLAINTEXT

The user-visible name of the class.

Description

1*128PLAINTEXT

A user-visible description of the class.

TableVersion

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

TableDate

RETSDATETIME

UpdateVersion 1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

UpdateDate

RETSDATETIME

ClassTimeStamp RETSNAME

DeletedFlag-
Field

RETSNAME

Deleted-
FlagValue

1*32ALPHANUM

HasKeyIndex

BOOLEAN

11.3.2 Table

The version of the Table metadata that describes this 
Class. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. 
The version number is advisory only.

The date on which the Table metadata for this Class 
was last changed. Clients MAY rely on this date for 
cache management.

The latest version of any of the Update metadata for 
this Class. The convention used is a 
“<major>.<minor>.<release>” numbering scheme. A 
blank version indicates no Update is available for this 
Class. The version number is advisory only.

The date on which any of the Update metadata for this 
Class was last changed. Clients MAY rely on this data 
for cache management. A blank date indicates no 
Update is available for this Class.

The SystemName of the field in the METADATA-
TABLE that acts as the last-change timestamp for this 
class.

The SystemName of the field in the METADATA-
TABLE that indicates that the record is logically 
deleted. If this element is specified, then Deleted-
FlagValue MUST be specified as well. 

The value of the field designated by DeletedFlag-
Field indicating that a record has been logically 
deleted. If the type of the field named by Deleted-
FlagField is numeric, then this value is converted 
to a number before comparison. If the type of the field 
named by DeletedFlagField is character, then 
the shorter of the two values is padded with blanks 
and the comparison made for equal length. 

When true, indicates that the Class supports the 
retrieval of key data for fields advertised in the Table 
Metadata as InKeyIndex.

The following table lists the minimum acceptable content for server-supplied metadata 
used in describing a table.

Version 1.7.2

  11-11

COMPACT header tag: METADATA-TABLE

Table 11-11 Table Metadata Compact Header Attributes  

Attribute

Version

Date

Resource
Class

Content

This is the version of the Table metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource in which this table resides.
The ClassName for the class in which this table resides.

Table 11-12 Metadata Content - Tables (Sheet 1 of 4)

Field Name

Content Type

Description

MetadataEn-
tryID

RETSID

SystemName

RETSNAME

StandardName

RETSNAME

LongName

1*256TEXT

DBName

1*10ALPHANUM

ShortName

1*64TEXT

MaximumLength POSITIVENUM

A value that never changes as long as the semantic 
definition of this field remains unchanged. In particu-
lar, it should be managed so as to allow the client to 
detect changes to the SystemName.

The name of the field as it is known to the native 
server. The system name MUST be unique within the 
Table.

The name of the field as it is known in the Real Estate 
Transaction XML DTD.

The name of the field as it is known to the user. This is 
a localizable, human-readable string. Use of this field 
is implementation-defined; it is expected that clients 
will use this value as a title for this datum when it 
appears on a report.

A short name that can be used as a database field 
name. This name may not start with a number nor can 
it be an ANSI-SQL92 reserved word. This value can be 
used by a client as the name of an internal database 
field, so servers should attempt to provide a value for 
this field that is unique within the table.

An abbreviated field name that is also localizable and 
human-readable. Use of this field is implementation-
defined. It is expected that clients will use this field in 
human-interface elements such as pick lists.

The maximum possible unencoded length of a value 
of this field. Given that the HTTP transport specifica-
tion converts all data types to a string representation 
and that certain characters and entities may be 
encoded for transmission, this is the maximum num-
ber of unencoded characters that can be expected for a 
single instance of this field. See Appendix D for inter-
pretation.

11-12  Real Estate Transaction Specification

Version 1.7.2

Table 11-12 Metadata Content - Tables (Sheet 2 of 4)

Field Name

Content Type

Description

DataType

Boolean

Character

Date

DateTime

Time

Tiny

Small

Int

Long

Decimal

A truth-value, stored using TRUE and FALSE. That 
is 1 for true and 0 for false.

An arbitrary sequence of printable characters.

A date in RETSDATE format.

A date and time in full-date format.

A time in RETSTIME format.

A signed numeric value that can be stored in no more 
than 8 bits.

A signed numeric value that can be stored in no more 
than 16 bits.

A signed numeric value that can be stored in no more 
than 32 bits.

A signed numeric value that can be stored in no more 
than 64 bits.

A decimal value that contains a decimal point (see 
Precision).

Precision

OPTNONNEGATIVENU
M

The number of digits to the right of the decimal point 
when formatted. Applies to Decimal fields only.

Searchable

BOOLEAN

When true, indicates that the field is searchable.

Version 1.7.2

  11-13

Table 11-12 Metadata Content - Tables (Sheet 3 of 4)

Field Name

Content Type

Description

Interpretation

Number

An arbitrary number.

Currency

Lookup

LookupMulti

LookupBit-
string[depre-
cated]

LookupBit-
mask[deprecated]

Alignment

Left

Right

Center

Justify

UseSeparator

BOOLEAN

EditMaskID

RETSNAME *(“,” 
RETSNAME)

LookupName

RETSNAME

A number representing a currency value.

A value that should be looked up in the Lookup Table. 
This is a single selection type lookup (e.g. STATUS).

This interpretation is also valid for Boolean data types, 
in which case the LookupType specified by the 
LookupName entry MUST contain exactly two ele-
ments, one with a Value of 0, and the other with a 
Value of 1.

A value that should be looked up in the Lookup Table. 
This is a multiple-selection type lookup (e.g. FEA-
TURES) where the character strings representing each 
selection are separated by commas.The character 
strings MAY be quoted text following the rules for 
Value of section 11.4.3 Lookup Type.

[deprecated]A value that should be looked up in the 
Lookup Table. This is a multiple-selection lookup that 
is stored as a bit string. The bit string is represented as 
a character string containing only the characters 0 and 
1. The leftmost character represents the least-signifi-
cant bit. The lookup value of the bitstring element is 
the ordinal position of each bit with the rightmost bit 
designated as bit 0.

[deprecated]A value that should be looked up in the 
Lookup Table. This is a multiple-selection type lookup 
that is stored as a bitmask field. Fields of this type are 
limited to 31 choices.(e.g. VIEW). When converted to 
binary, each bit represents one of the possible choices. 
The choices are from lsb to msb. Lookup values are 
the numeric equivalent of each bit’s binary value (i.e., 
the low order bit represents the first lookup and the 
high order bit represents the last lookup choice). 
2(value–1) is added to the total choice when querying 
for its applicability.

The value MAY be displayed left aligned.

The value MAY be displayed right aligned.

The value MAY be centered in its field when dis-
played.

The value MAY be justified within its field when dis-
played.

When true, indicates that the numeric value MAY be 
displayed with a thousands separator.

For each RETSNAME, the name of the METADATA-
EDITMASK EditMaskID containing the edit mask 
expression for this field (see Section 11.4.5 ). Multiple 
masks are permitted and are separated by commas.

The name of the METADATA-LOOKUP containing 
the lookup data for this field (see Section 11.4.2). 
Required if Interpretation is Lookup, LookupMulti, 
LookupBitstring or LookupBitmask.

11-14  Real Estate Transaction Specification

Version 1.7.2

Table 11-12 Metadata Content - Tables (Sheet 4 of 4)

Field Name

Content Type

Description

MaxSelect

Numeric

This field is required if Interpretation is Lookup-
Multi, LookupBitstring or LookupBit-
mask. This value indicates the maximum number of 
entries that may be selected in the lookup.

Units

(Feet | Meters | 
SqFt | SqMeters |
Acres | Hectares )

Unit of measure.

Index

BOOLEAN

Minimum

Numeric

Maximum

Numeric

Default

SERIAL

Required

Numeric

SearchHelpID

RETSNAME

Unique

BOOLEAN

ModTimeStamp

BOOLEAN

ForeignKeyName RETSID

ForeignField

RETSNAME

KeyQuery[depre-
cated]

BOOLEAN

KeySelect[depre-
cated]

BOOLEAN

InKeyIndex

BOOLEAN

When true, indicates that this field is part of an index. 
The client MAY use this information to help the user 
create faster queries.

The minimum value that may be stored in a field 
(applies to numeric fields only).

The maximum value that may be stored in a field 
(applies to numeric fields only).

The order that fields should appear in a default one-
line search result. Fields that should not appear in the 
default one-line format should have a value of 0, 
Fields that should never be visible to the user should 
have a value of –1.

A non-zero value indicates the field is required when 
searching. This value should be sequential starting 
with one. If multiple fields share the same value, then 
one of the fields with the same value is required. (e.g. 
City = 1 & ZipCode = 1 implies that the user is 
required to include either City or ZipCode in their 
query).

The name of the entry in the METADATA-
SEARCH_HELP table (see Section 11.4.4).

When true, indicates that this field is a unique identi-
fier for the record that it is part of.

When true, indicates that changes to this field update 
the class’s ModTimeStamp field.

When nonblank, indicates that this field is normally 
populated via a foreign key. The value is the For-
eignKeyID from the METADATA-FOREIGNKEYS 
table.

The SystemName from the child record accessed via 
the specified foreign key.

When true, indicates that this field may be included in 
a query that uses the Key optional argument.[depre-
cated]

When true, indicates that this field may be included in 
the Select list of a query that uses the Key optional 
argument.[deprecated]

When true, indicates that this field may be included in 
the Select argument of a Search to suppress normal 
Limit behavior following the rule described in Section 
7.4.5

Version 1.7.2

  11-15

11.3.3 Update

A given data resource may contain multiple classes of entries that can be updated 
separately. The metadata for a resource supporting updateable classes MUST contain a 
Class Table description for each class supported.

COMPACT header tag: METADATA-UPDATE

Table 11-13 Update Metadata Compact Header Attributes  

Attribute

Version

Date

Resource
Class

Content

This is the version of the Update metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource to which this metadata table applies.
The ClassName for the class to which this metadata table applies.

Table 11-14 Metadata Content – Update 

Metadata Field

Content Type

Description

MetadataEn-
tryID

RETSID

A value that never changes so long as the semantic definition of this entry 
remains unchanged.

UpdateName

1*24ALPHANUM

This identifies the nature of the update, such as "add" or "modify". Some 
update types, such as changes to a property record (e.g. "Sell", "Back on 
Market"), will imply a set of business rules specific to the server. However, 
where possible, the following standard type names should be used:

Update Name

Function

Add

Clone

Change

Delete

Add a new record

Create a new record by copying an old one

Change an existing record

Delete an existing record

Description

1*64PLAINTEXT

A user visible description of the Update Type.

KeyField

RETSNAME

The SystemName (see Section 11.3.2) of the field that must be used to 
retrieve an existing record for the update.

UpdateTypeVer-
sion

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

The latest version of this Update Type metadata. The convention used is a 
“<major>.<minor>.<release>” numbering scheme. The version number is 
advisory only. 

UpdateTypeDate RETSDATETIME

The date on which any of the content of this Update Type was last 
changed. Clients MAY rely on this date for cache management.

11.3.4 Update Type

A given resource may contain multiple classes of entries that can be updated separately. 
Each of these classes may have different types of updates that can be performed. There 
might be different test expressions or sequences. This section describes how each of those 
are specified.

11-16  Real Estate Transaction Specification

Version 1.7.2

COMPACT header tag: METDATA-UPDATE_TYPE

Table 11-15 UpdateType Metadata Compact Header Attributes  

Attribute

Version

Date

Resource
Class
Update

Content

This is the version of the Update Type metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource to which this metadata table applies.
The ClassName for the class to which this metadata table applies.
The UpdateName for the Update to which this metadata table applies.

Table 11-16 Metadata Content – Update Type  

Metadata Field

Content Type

Description

MetadataEn-
tryID

SystemName

Sequence

RETSID

RETSNAME

1*5DIGIT

A value that never changes as long as the semantic definition of this entry 
remains unchanged.

This is the SystemName of the field as defined in Section 11.3.2.

Sequence number of the field, representing the order of entry

Attributes

1*(1 | 2 | 3 | 4 | 5 [,])

Multiple entries are separated by commas.

Value

Meaning

Description

1

2

3

4

5

DisplayOnly

Field may not be changed.

Required

Autopop

Interactive-
Validate

Field may not be left blank.

Field is populated by the server.

When changed, the client can validate 
the field only by contacting the server. 
All fields listed as “AdditionalField” 
MUST also be passed.

ClearOnCloning

The field should be cleared when the 
containing record is cloned.

Default

<PLAINTEXT>

Default value of field (i.e. value if not specified by user)

ValidationEx-
pressionID

RETSNAME *(“,” 
RETSNAME)

UpdateHelpID

RETSNAME

<multiple entries are separated by commas>

The names of the ValidationExpressions to use. See section 11.4.9

The name of the entry in the METADATA-UPDATE_HELP table (see Sec-
tion 11.4.6).

Validation-
LookupName

ValidationEx-
ternalName

RETSNAME

The name of the ValidationLookup to use. See section 11.4.7

RETSNAME

The name of the ValidationExternal to use. See section 11.4.10

MaxUpdate

1*5DIGIT

For LookupMulti fields, the maximum number of values that may be spec-
ified for the field. This value has no meaning for fields with any other 
interpretation.

Version 1.7.2

  11-17

11.4 Metadata Format for Shared Elements

11.4.1 Object

Object type names allow the operator of a particular server to advertise its supported 
multimedia types. These types are standard MIME types as registered with IANA. RETS 
does not require that a server make available any particular type of multimedia object. 
However, a server MUST use a standard well-known name under which to make its 
multimedia objects available, if a suitable well-known name is defined in the standard. 
Multimedia names are defined in Table 11-17.

Table 11-17 Well-known Object Types  

Object Name

Purpose

Photo

Plat

Video

Audio

Thumbnail

Map

VRImage

A representation image related to the element defined by the resource Key-
Field.

An image of the property boundaries related to the element defined by the 
resource KeyField

A moving image with or without sound related to the element defined by the 
resource KeyField.

A sound clip related to the element defined by the resource KeyField.

A lower-resolution image related to the element defined by the resource Key-
Field.

A location image related to the element defined by the resource KeyField.

A multiple-view, possibly-interactive image related to the element defined by 
the resource KeyField.

COMPACT header tag: METDATA-OBJECT

Table 11-18 Object Metadata Compact Header Attributes  

Attribute

Version

Date

Resource

Content

This is the version of the Object metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource to which this metadata table applies.

Table 11-19 Metadata Content: Resource Object  (Sheet 1 of 2)

Metadata Field

Content Type

Description

MetadataEn-
tryID

RETSID

ObjectType

1*24ALPHANUM

MIMEType

A MIME type per 
RFC 2045

A value that never changes as long as the semantic defi-
nition of this field remains unchanged.

The classification of the object. If one of the well-known 
object types in Table 11-17 applies, then it MUST be 
used.

The name of the object type. This is the MIME type that 
a client can pass to the "Accept" parameter in the Get 
Object transaction (see Section 5.1).

11-18  Real Estate Transaction Specification

Version 1.7.2

11.4.2 Lookup

Table 11-19 Metadata Content: Resource Object  (Sheet 2 of 2)

Metadata Field

Content Type

Description

VisibleName

1*64PLAINTEXT

The user-visible name of the object type.

Description

1*128PLAINTEXT

A user-visible description of the object type.

ObjectTime-
Stamp

RETSNAME

ObjectCount

RETSNAME

The SystemName of the field in a METADATA-
TABLE that acts as the timestamp for objects of this 
type. This SystemName MUST be one that appears in 
every class that has objects of this type.

The SystemName of the field in a METADATA-
TABLE that acts as the count for objects of this type. 
This SystemName MUST be one that appears in every 
class that has objects of this type.

This section describes the lookup tables that are referenced by the LookupName in the 
Table section. There MUST be a corresponding lookup table for every "LookupName".

COMPACT header tag: METADATA-LOOKUP

Table 11-20 Lookup Metadata Compact Header Attributes  

Attribute

Version

Date

Resource

Content

This is the version of the Lookup metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource in which this table resides.

Table 11-21 Metadata Content: Lookup  

Field Name

Content Type

Description

MetadataEn-
tryID

RETSID

LookupName

RETSNAME

A value that never changes as long as the semantic defini-
tion of this entry remains unchanged.

The name of Lookup Table. There MUST be an entry for 
each LookupName value used in the Table metadata.

VisibleName

1*64PLAINTEXT

A description of the table that is human-readable.

Version

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

The latest version of this Lookup Table metadata. The 
convention used is a “<major>.<minor>.<release>” num-
bering scheme. The version number is advisory only. 

Date

RETSDATETIME

The date on which any of the content of this Lookup was 
last changed. Clients MAY rely on this date for cache 
management.

11.4.3 Lookup Type

This section describes the content of a lookup table that is referenced by the LookupName 
in the Table section. There MUST be a corresponding lookup table for every "Lookup", 
“LookupMulti”, “LookupBitstring” and “LookupBitmask”.

Version 1.7.2

  11-19

COMPACT header tag: METADATA-LOOKUP_TYPE

Table 11-22 Lookup Type Metadata Compact Header Attributes  

Attribute

Version

Date

Resource
Lookup

Content

This is the version of the Lookup Type metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource in which this table resides.
The LookupName for the class in which this table resides.

Table 11-23 Metadata Content: Lookup Type  

Field Name

Content Type

Description

MetadataEn-
tryID

RETSID

A value that never changes so long as the semantic definition 
of this entry remains unchanged. In particular, it should be 
managed so as to allow the client to detect changes to the 
Value.

LongValue

1*128PLAINTEXT The value of the field as it is known to the user. This is a 

ShortValue

1*32PLAINTEXT

localizable, human-readable string. Use of this field is imple-
mentation-defined; expected uses include displays on reports 
and other presentation contexts. This is the value that is 
returned for a COMPACT-DECODED or STANDARD-
XML format request.

An abbreviated field value that is also localizable and human-
readable. Use of this field is implementation-defined; 
expected uses include picklist values and other human inter-
face elements.

Value

1*128PLAINTEXT The value to be sent to the server when performing a search. 
[(deprecated)This field must be numeric for LookupBitmask 
and LookupBitstring types. For LookupBitmask fields, 
2(value-1) is used to compute this component as part of the 
applicable choices. For LookupBitstring fields, this is the 
position with in the field, 1-based, at which the value con-
tains a“1”.] This is the value that is returned for a COM-
PACT format request.

11.4.4 Search Help

This section describes the Search Help text tables that are referenced in the Table section. 
There MUST be a corresponding table entry for each Search HelpTextID referenced in the 
METADATA-TABLE.

11-20  Real Estate Transaction Specification

Version 1.7.2

COMPACT header tag: METADATA-SEARCH_HELP

Table 11-24 Search Help Metadata Compact Header Attributes  

Attribute

Version

Date

Resource

Content

This is the version of the Search Help metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource to which this metadata table applies.

Table 11-25 Metadata Content: Search Help  

Field Name

Content Type

Description

MetadataEn-
tryID

RETSID

SearchHelpID

RETSNAME

A value that never changes so long as the semantic 
definition of this entry remains unchanged.

A unique ID for the help text. This ID is referenced as 
the SearchHelpID in section 11.3.2 

Value

1*1024TEXT

The value to be displayed to the user.

11.4.5 Edit Mask

This section describes the Edit Mask table that is referenced in the Table section. There 
MUST be a corresponding table entry for each Search EditMaskID referenced in the 
METADATA-TABLE.

A Regular Expression is used to define the edit mask. Table 11-28 describes the structures 
that make up RETS regular expressions.

COMPACT header tag: METADATA-EDITMASK

Table 11-26 EditMask Metadata Compact Header Attributes  

Attribute

Version

Date

Resource

Content

This is the version of the Edit Mask metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource to which this metadata table applies.

Table 11-27 Metadata Content: Edit Mask  

Field Name

Content Type

Description

MetadataEn-
tryID

RETSID

EditMaskID

RETSNAME

A value that remains unchanged so long as the seman-
tic definition of this field remains unchanged.

A unique ID for the Edit Mask. This ID is referenced 
as the EditMaskID in section 11.3.2 

Value

1*256TEXT

The Regular Expression to be used.

Version 1.7.2

  11-21

RETS Regular Expression Specification

RETS regular expressions are a subset of POSIX 1003.2 extended regular expressions [12], 
supporting the metacharacters in Table 11-28. 

Table 11-28 RETS Regular Expression Metacharacters 

Metacharacter

Function

. (period)

Matches any single character

*

+

?

|

( ) parentheses

{min[,max]} 
(braces)

[ ] brackets

Matches zero or more of the preceding pattern

Matches one or more of the preceding pattern

Matches zero or one of the preceding pattern

Alternation: used between two subpatterns, matches either the one to its left 
or the one to its right.

Grouping: causes the enclosed pattern to be treated as atomic. Parentheses 
may not be nested; that is, only one level of grouping is required.

Quantifier: matches at least min and at most max of the preceding pattern, 
where min and max are both nonnegative integer values. If max is omitted, 
matches exactly min of the preceding pattern.

Character class: matches any of the characters contained in the brackets. 
Except for the circumflex, described below, and the closing bracket, charac-
ters within a character class are never treated as metacharacters.

^ (circumflex)

Used as the first character of a character class, reverses the sense of the charac-
ter class; for example, [^0] matches any character except a “0”.

-

\

Operates only within brackets. Except as the first or last character, denotes a 
range of characters on the default host collating sequence. For example, [0-9] 
matches any digit. When - is the first or the last character, it is treated as a 
member of the character class.

Escape: treats the following character as an ordinary character rather than a 
metacharacter. For example, \* matches a single asterisk. The \ character itself 
must be escaped. The escape character is not needed within character classes.

The following is a simple example:

[0-9]+[a-fA-F][1-8][A]?[0-9]{2}[A-C]{1,3}

One or more digits, followed by an upper or lower case letter A - F, followed by a digit 1 – 
8, optionally followed by one letter A, followed by two digits 0 – 9, followed by between 
one and three of the letters A – C.

A phone number example:

[0-9]{3}-[0-9]{4}

11.4.6 Update Help

This section describes the Update Help Text tables that are referenced in the Update Type 
section of the document. There MUST be a corresponding table entry for each Update 
Help Text ID referenced in any of the METADATA-UPDATE_TYPEs.

11-22  Real Estate Transaction Specification

Version 1.7.2

COMPACT header tag: METADATA-UPDATE_HELP

Table 11-29 Update Help Metadata Compact Header Attributes  

Attribute

Version

Date

Resource

Content

This is the version of the Update Help metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource to which this metadata segment belongs.

Table 11-30 Metadata Content: Update Help  

Field Name

Content Type

Description

MetadataEn-
tryID

RETSID

UpdateHelpID

RETSNAME

A value that remains unchanged so long as the seman-
tic definition of this entry remains unchanged.

A unique ID for the help text. This ID is referenced as 
the UpdateHelpID in section 11.4.6. 

Value

1*1024TEXT

The value to be displayed to the user.

11.4.7 Validation Lookup

This section describes the Validation Lookup tables that are referenced in the Update Type 
section of the document. There MUST be a corresponding Validation Lookup Table for 
each one referenced in the METADATA-UPDATE_TYPEs.
COMPACT header tag: METADATA-VALIDATION_LOOKUP

Table 11-31 ValidationLookup Metadata Compact Header Attributes  

Attribute

Version

Date

Resource

Content

This is the version of the Table metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource in which this table resides.

Table 11-32 Metadata Content: Validation Lookup  (Sheet 1 of 2)

Field Name

Content Type

Description

MetadataEn-
tryID

Validation-
LookupName

RETSID

RETSNAME

Parent1Field

RETSNAME

Parent2Field

RETSNAME

A value that remains unchanged so long as the semantic 
definition of this entry remains unchanged.

The unique name of this Validation Lookup. Each Name in 
the Update Type ValidationLookupName field MUST have 
a definition.

If a value is present, it is a SystemName field in the same 
table as defined in Section 11.3.2 and indicates a depen-
dency on this field.

If a value is present it is a SystemName field in the same 
table as defined in Section 11.3.2 and indicates an addi-
tional dependency on this field.

Version 1.7.2

  11-23

Table 11-32 Metadata Content: Validation Lookup  (Sheet 2 of 2)

Field Name

Content Type

Description

Version

Date

1*2DIGITS . 
1*2DIGITS . 
1*5DIGITS

The version of this Validation Lookup metadata. The con-
vention used is a “<major>.<minor>.<release>” numbering 
scheme. This version number is advisory only.

RETSDATETIME The date on which any of the content of this Validation 
Lookup metadata was last changed. Clients MAY rely on 
this date for cache management.

11.4.8 Validation Lookup Type

This section describes the content of the Validation Lookup tables that are referenced in 
the Table section of the document. There MUST be a corresponding Validation Lookup 
Type table for each one referenced in the METADATA-UPDATE_TYPE.

The Validation Lookup Type provides a list of all the valid values for a field. This is 
different than the Lookup described in Section 11.4.2. The Validation Lookup is used for 
two cases: 1) the list is too long to be provided as a standard lookup (e.g. Street Name) and 
2) there is a dependency on the value in another field. For example, a valid entry for a 
School District might depend on the Area and SubArea that is entered. 

COMPACT header name: METADATA-VALIDATION_LOOKUP_TYPE 

Table 11-33 Validation Lookup Type Metadata Compact Header Attributes  

Attribute

Version

Date

Resource
Validation-
Lookup

Content

This is the version of the Validation Lookup metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource in which this table resides.
The ValidationLookupName for the METADATA-VALIDATION_LOOKUP entry to 
which this entry belongs.

Table 11-34 Metadata Content: Validation Lookup Type  

Field Name

Content Type

Description

MetadataEn-
tryID

RETSID

A value that remains unchanged so long as the semantic 
definition of the entry remains unchanged.

ValidText

RETSNAME

A valid value for the field.

Parent1Value

RETSNAME

Parent2Value

RETSNAME

If this field is present then the ValidText can be used if 
the data in the Parent1 field is set to this value. If Parent1 
is present in the PARENTFIELDS tag then this field is 
required.

If this field is present then the ValidText can be used if 
the data in the Parent2 field is set to this value. If Parent2 
is present in the PARENTFIELDS tag then this field is 
required.

11-24  Real Estate Transaction Specification

Version 1.7.2

11.4.9 Validation Expression

This section describes the ValidationExpression table that is referenced in Section 11.3.4. 
There MUST be a corresponding table entry for each ValidationExpressionID referenced 
in the METADATA-UPDATE_TYPEs for a Resource.

The table contains expressions that are to be evaluated when a field value is entered by the 
user. Expressions in the list MUST be evaluated in the order in which they appear in the 
list. There are three types of validation expressions, each introduced by a reserved token 
preceding the expression, given in Table 11-35:

Table 11-35 Validation Expression Types  

Keyword

Type

Purpose

ACCEPT

Boolean

REJECT

Boolean

SET

Assignment

If the expression is true, the field value is considered accepted without 
further testing. Subsequent SET expressions MUST be executed.

If the expression is true, the field value is considered rejected without 
further testing. Subsequent SET expressions MUST NOT be evaluated.

The expression MUST begin with a field name and an equal sign (“=”). 
The following expression is evaluated and the result stored in the desig-
nated field. 

Expressions are algebraic formulas containing keywords and operators. Expressions may 
contain parentheses, and consist of keywords representing any of: 

• The current value of any field in the input list

• The current value of any Well-Known Name field in the user’s agent record that is 
returned in the response to the login transaction (see 4.9, “Well-Known Names”). 

• Literal values.

• A special token (Table 11-18 Metadata Content – Validation Expression Special 

Operand Tokens).

together with the operators in Table 11-36. Arithmetic operations MUST be carried out 
using IEEE-754 arithmetic with a representation of at least 64 bits. Comparison operations 
on strings MUST use simple binary collation. If an error or arithmetic exception occurs 

Version 1.7.2

  11-25

during expression evaluation, field value is considered erroneous, regardless of the 
expression type.

Table 11-36 Validation Expression Operators  

Operator

/, *, .MOD.

+,–

.CONTAINS.

<, >, <=, >=,

=, !=

.AND.

.OR.

.NOT.

Prece-
dence

Operation

1

2

2

3

4

5

6

7

Division, multiplication, and remainder (modulo)

Addition and subtraction, applied as follows:

1. If both operands are numeric, the operation is algebraic.

2. If either operand is a string, it is converted to numeric and the oper-
ation is algebraic. If an error occurs during the conversion, the field 
value MUST be rejected.

3. For “+”, if either operand is a date, the other must be an integer, a 
string that can be converted to an integer, or a string representing an 
interval in ISO8601 format. If no conversion is possible, the field value 
MUST be rejected

4. For “-”, if the left operand is a date or time, the other operand must 
be a date, a time, or a string representing an interval, and the result 
must be a string representing an interval in ISO8601 format.

A Boolean operator taking strings as its left and right operands. The 
operation is TRUE if the left operand contains the right operand as a 
substring anywhere within it.

Comparison operators with their conventional meaning. If one oper-
and is numeric and the other is a string, the string MUST be converted 
to a number prior to the comparison. If an error occurs during the con-
version, the field value must be rejected.

Comparison operators with their conventional meaning. If one oper-
and is numeric and the other is a string, the string MUST be converted 
to a number prior to the comparison. If an error occurs during the con-
version, the field value must be rejected.

A Boolean operator that takes two Boolean operands, and whose value 
is TRUE if and only if both of its operands are TRUE.

A Boolean operator that takes two Boolean operands, and whose value 
is TRUE if either of its operands is TRUE.

A Boolean operator that takes a single Boolean operand and returns its 
inverse.

Literal values to be compared against dates or times are expressed in the ISO8601 format.

Table 11-37 Validation Expression Special Operand Tokens (Sheet 1 of 2)

Token

.TODAY.

.NOW.

.ENTRY.

.EMPTY.

Value

The current date.

The current time.

The current field text, as a string.

A value that matches an empty or all-blank field. Supplies an empty (zero-
length) field when used in a SET expression.

.OLDVALUE.

The text that was in the field as returned from the host in the search opera-
tion. If the field is new, .OLDVALUE. is an empty string.

.USERID.

The value of the user-id field returned in the Login transaction (Section 4.9).

11-26  Real Estate Transaction Specification

Version 1.7.2

Table 11-37 Validation Expression Special Operand Tokens (Sheet 2 of 2)

Token

Value

.USERCLASS.

.USERLEVEL.

.AGENTCODE.

The value of the user-class field returned in the Login transaction (Section 
4.9).

The value of the user-level field returned in the Login transaction (Section 
4.9).

The value of the agent-code field returned in the Login transaction (Section 
4.9).

.BROKERCODE.

The value of the broker-code field returned in the Login transaction (Section 
4.9).

.BROKERBRANCH.

The value of the broker-branch field returned in the Login transaction (Sec-
tion 4.9).

The Validation Expression metadata starts with a <METADATA-VALIDATION_EXPRESSION> 
tag 

COMPACT header tag: METADATA-VALIDATION_EXPRESSION

Table 11-38 Validation Expression Metadata Compact Header Attributes  

Attribute

Version

Date

Resource

Content

This is the version of the Validation Expression metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource to which this metadata table applies.

Table 11-39 Metadata Content: Validation Expression 

Field Name

Content Type

Description

MetadataEn-
tryID

Validation-
ExpressionID

RETSID

RETSNAME

A value that remains unchanged so long as the semantic 
definition of this entry remains unchanged.

A unique ID for the ValidationExpression. This ID is 
referenced as the ValidationExpression in Section 
11.3.4.

Validation-
ExpressionType

1*32ALPHANUM

A validation expression type from Table 11-35.

Value

1*512TEXT

The test expression to be evaluated.

11.4.10 Validation External

This section describes the Validation External tables that are referenced in the Update 
Type section of the document. There MUST be a corresponding Validation External table 
for each one referenced in any of the METADATA-UPDATE_TYPEs for the Resource.

Version 1.7.2

  11-27

COMPACT header tag: METADATA-VALIDATION_EXTERNAL

Table 11-40 Validation External Metadata Compact Header Attributes  

Attribute

Version

Date

Resource

Content

This is the version of the Validation External metadata. The convention used is a 
"<major>.<minor>.<release>" numbering scheme. Every time any contained metadata 
element changes the version number MUST be increased.
The latest change date of any contained metadata. This MUST be in the format 
described in chapter 2 for RETSDATETIME.
The ResourceID for the resource to which this metadata table applies.

Table 11-41 Metadata Content: Validation External  

Field Name

Content Type

Description

MetadataEn-
tryID

ValidationEx-
ternalName

RETSID

RETSNAME

SearchResource RETSNAME

SearchClass

RETSNAME

A value that remains unchanged so long as the semantic 
definition of this entry remains unchanged.

The unique name of this Validation External. Each Name 
in the Update Type ValidationExternalName field MUST 
have a definition.

The ResourceID of the Resource to be searched from 
11.2.2.

The ClassName within the Resource to be searched 
from 11.3.1.

Version

1*2DIGITS "." 
1*2DIGITS "." 
1*5DIGITS

The latest version of this Validation External metadata. 
The convention used is a “<major>.<minor>.<release>” 
numbering scheme. The version number is advisory only.

Date

RETSDATETIME

The date on which any of the content of this Validation 
External was last changed. Clients MAY rely on this date 
for cache management.

11.4.11 Validation External Type

This section describes the content of the Validation External Type tables that are 
referenced in the Table section of the document. There MUST be a corresponding 
Validation External Type table for each one referenced in the METADATA-UPDATE_TYPEs 
for the Resource.

The Validation External Type provides lists of search, display, and results fields. The 
Validation External may be used for several cases: 1) The database involved is too large or 
dynamic to be provided as a standard lookup (e.g. Tax). 2) There are business rules that 
can only be enforced on the server (e.g. expiration dates). 3) The content of a field 
populates fields from another database (e.g. Sale_agent_name, Sale_office_name, 
Sale_office_id from Sale_agent_id).

11-28  Real Estate Transaction Specification

Version 1.7.2

COMPACT header tag: METADATA-VALIDATION_EXTERNAL_TYPE

Table 11-42 Validation External Type Metadata Compact Header Attributes  

Attribute

Content

Version

This is the version of the Validation External Type metadata. The con-
vention used is a "<major>.<minor>.<release>" numbering scheme. 
Every time any contained metadata element changes the version num-
ber MUST be increased.
The latest change date of any contained metadata. This MUST be in the 
format described in chapter 2 for RETSDATETIME.
Resource
The ResourceID for the resource to which this metadata table applies.
ValidationExternalName The ValidationExternalName to which this entry type applies.

Date

Table 11-43 Metadata Content: Validation External Type 

Field Name

Content Type

Description

MetadataEn-
tryID

RETSID

SearchField

1*512PLAINTEXT

DisplayField

1*512PLAINTEXT

A value that remains unchanged so long as the seman-
tic definition of this entry remains unchanged.

A comma separated list of valid fields using System-
Name from Section 11.3.2. 

A comma separated list of valid fields using System-
Name from Section 11.3.2.

ResultFields

1*1024PLAINTEXT A comma separated list of valid field pairs joined by = 

(equal) the first is a target field in the table being 
updated and the second is a source field in the table 
being searched. The fields use a SystemName from 
Section 11.3.2.

Version 1.7.2

  11-29

11-30  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0GETMETADATA TRANSACTION

The GetMetadata transaction is used to retrieve structured information known as 
metadata related to the system entities. Metadata requested and returned from this 
transaction are requested and returned as MIME media types.

12.1 Required Client Request Header Fields

There are no additional required client header fields.

12.2 Required Request Arguments

Type

::= <A grouping of related metadata elements (see Section 11)>

The type of metadata being requested. The Type MUST begin with METADATA and 
MAY be one of the defined metadata types (see Section 11).

 ID

::= metadata-id[: metadata-id]

metadata-id

::= 1*ALPHANUM | *

Metadata is organized hierarchically. Each level specifies in its first field an identifier for 
the metadata contained within that level (e.g. for the Resource level: ResourceID--Agent, 
Property, etc. for the Lookup level: LookupName—Status, Area, etc.). This identifier can 
be used to restrict requests to the Type metadata contained within specific instances of 
higher levels. If the last metadata-id is 0 (zero), then the request is for all Type metadata 
contained within that level; if the last metadata-id is “*”, then the request is for all Type 
metadata contained within that level and all metadata Types contained within the 
requested Type. This means that for a metadata-id of METADATA-SYSTEM, for example, the 
server is expected to return all metadata.

Note: The metadata-id for METADATA-SYSTEM and METADATA-RESOURCE must be 0 or *.

12.3 Optional Request Arguments

Format

version

= COMPACT | STANDARD-XML | STANDARD-XML:version

::= <RETS metadata public identifier>

Version 1.7.2

  12-1

“COMPACT” means a table descriptor, field list <COLUMNS> followed by a delimited set of 
the data fields. See Section 11 for more information on the COMPACT formats. 
“STANDARD-XML” means an XML presentation of the data in the format defined by the 
RETS Metadata XML DTD. Servers MUST support all formats. If the format is not 
specified, the STANDARD-XML presentation will be returned.

When the client requests the STANDARD-XML representation, it MAY also specify the 
public identifier of the DTD that it expects. The server MUST support the current version 
and SHOULD support the prior version.

12.4 Required Server Response Header Fields

In addition to the other Required Server Header Fields specified in Section 3.3 the 
following response header fields are required.

Content-Type

The media type of the underlying data. The server MUST return 
this field in all replies. This field MUST be set to the type of media 
returned.

Content-Type ::= Content-Type : type / subtype

Example:

Content-Type: text/xml

12.5 Required Response Arguments

There are no required response arguments.

12.6 Optional Response Arguments

There are no optional response arguments.

12.7 Metadata Response Body Format

The body of the metadata response has the following format when replying to a request 
with the format set to “COMPACT”:

<RETS 1*SP ReplyCode=quoted-reply-code 1*SP 

ReplyText=quoted-string *SP > CRLF 

[*metadata-segment] 
[ rets-status-tag] 
</RETS> CRLF

metadata-segment::= <A metadata segment as defined in Section 11.>

The body of the metadata response has the following format when replying to a format 
request of "STANDARD-XML" data:

<?xml version="1.0" ?> 
[doctype] 
<RETS 1*SP ReplyCode=quoted-reply-code 1*SP 

ReplyText=quoted-string *SP >  

[*XML-metadata-segment] 
[ rets-status-tag ]  
</RETS> CRLF

12-2  Real Estate Transaction Specification

Version 1.7.2

doctype

::= <!DOCTYPE RETS PUBLIC "-//RETS//DTD Metadata 

Content 1.7.2//EN">

XML-metadata-segment::=A metadata segment as defined by the RETS Metadata XML 

NOTE

DTD.

RETS 1.7.2 requires all server responses to be well-formed XML, and additionally requires GetMetadata 
responses to be valid XML. In addition, RETS requires that clients parse server responses as XML, not as 
simple text streams. The response formats shown here are normative with respect to content, but not 
normative with respect to form. That is, servers are free to produce response XML in any format that 
complies with the W3C XML 1.0 recommendation, so long as it is valid with respect to the appropriate DTD. 
XML escaping of content is implied, as is XML processing of whitespace and line endings. See the W3C XML 
Recommendation 1.0, Third Edition, for full information on XML.

12.8 Reply Codes

Table 12-1 GetMetadata Reply Codes  (Sheet 1 of 2)

Reply Code

Meaning

20500

20501

20502

20503

20506

20507

20508

20509

20510

20511

20512

Invalid Resource

The request could not be understood due to an unknown resource.

Invalid Type

The request could not be understood due to an unknown metadata type.

Invalid Identifier

The identifier is not known inside the specified resource.

No Metadata Found 

No matching metadata of the type requested was found.

Unsupported MIMEType

The server cannot return the metadata in any of the requested MIME types.

Unauthorized Retrieval

The metadata could not be retrieved because it requests metadata to which 
the supplied login does not grant access (e.g. Update Type data).

Resource Unavailable

The requested resource is currently unavailable.

Metadata Unavailable

The requested metadata is currently unavailable.

Request Too Large

Metadata could not be retrieved because a system limit was exceeded.

Timeout

The request timed out while executing.

Too many outstanding requests

The user has too many outstanding requests and new requests will not be 
accepted at this time.

Version 1.7.2

  12-3

Table 12-1 GetMetadata Reply Codes  (Sheet 2 of 2)

Reply Code

Meaning

20513

20514

Miscellaneous error

The server encountered an internal error.

Requested DTD version unavailable.

The client has requested the metadata in STANDARD-XML format using a 
DTD version that the server cannot provide.

12-4  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0COMPACT DATA FORMAT

Clients may choose to access data from a server in a compact data format that does not use 
full XML representation. When a client requests information from a compliant server in 
“COMPACT” or “COMPACT-DECODED” format, it will typically need to interpret the 
result by using the metadata that the server makes available.

13.1 Overall format

Compact format records are sequences of fields separated by delimiter. A tab character (an 
octet with a value of 09) is the default delimiter unless another is specified as part of the 
transaction. The delimiter MUST be some character other than the comma “,” character. 
This character is reserved for separating values in any field with an interpretation of 
LookupMulti where more than one value may be applied to that field. The sequence of 
fields MUST be described by a <COLUMNS> tag in the body of the message that carries the 
compressed records. No field described in the <COLUMNS> tag may be omitted from the 
<DATA>; if the value of a particular field for some record is undefined or is suppressed for 
authorization reasons, the value MUST be represented by two delimiters with no 
intervening space. No field omitted in the COLUMNS tag may be added in any DATA tag. The 
number of fields in the <COLUMNS> tag MUST match the number of fields in the <DATA> 
tags.

Each compact records is enclosed within a <DATA> start tag and a </DATA> end tag.

Fields with an interpretation of Lookup, LookupMulti, LookupBitstring or 
LookupBitMask contains the LookupType Value from Table 11-20 when the format is 
COMPACT and the LookupType LongValue from Table 11-20 when the format is 
COMPACT-DECODED.

13.2 Decoded Format

COMPACT-DECODED format requires sending field data in an expanded form. For 
example, if a field representing data for City is given the interpretation of Lookup in the 
Metadata, there will be a corresponding LookupType table that contains at least two 
values, Value and LongValue. It may also contain a ShortValue, but that is not relevant to 
the example. For this example, the Value is 101 and the LongValue is Anytown. In the 

Version 1.7.2

  13-1

COMPACT format, the returned data for this field is 101. This is referred to as the coded 
value. In the COMPACT-DECODED format case, the returned data for this field is 
Anytown. This is referred to as the decoded value. A server MUST perform the expansion 
from the Value to the LongValue for fields with an interpretation of Lookup, 
LookupMulti, LookupBitString or LookupBitMask.

13.3 Multivalued Fields

If the field is multivalued, values MUST be separated by commas and an optional space 
between each value. The final value does not have the comma or space before the field 
delimiter.

13.4 Transmission standards

A client or server transmitting a compact record MUST encode the data according to 
Table 13-1.

Table 13-1 Compact Data Field Format Representation 

Type

Numeric

Character

Date

Time

Date-Time

MultiSelect

Encoding Format

An optional negative sign, followed by zero or more digits, followed by an 
optional period, followed optionally by zero or more digits. The interpreta-
tion determines if an optional character may be included. A valid number 
MUST contain at least one digit if it includes a decimal point or sign. The 
value may contain leading zeros before the decimal point. The value may con-
tain trailing zeros after the decimal point and fraction, if any. Data types Tiny, 
Small, Int and Long (Table 11-12) may be signed but may not have a decimal 
point or fraction. Values with the interpretation LookupBitmask must not be 
signed, nor may they have nonzero digits after the decimal point.

The plain character sequence, except for LookupMulti, which contains multi-
ple sequences of characters separated by commas. Values with the interpreta-
tion LookupBitstring must contain only the characters “0” and “1”.
A date in full-date RETSDATE format.
A date in RETSTIME format.

A date in RETSDATETIME format.

A string consisting of one or more substrings, comma-delimited, each of 
which corresponds to an entry in the field’s associated MetadataLookup table.

Boolean

A single character, either 1 for true or 0 for false.

13-2  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0SESSION PROTOCOL

A RETS session follows a well-defined timing sequence in becoming established and in 
terminating. In particular, the authorization sequence MUST be followed in order to begin 
using other transactions within the protocol. The protocol contains four phases: 
connection establishment, authorization, session and termination.

14.1 Connection Establishment

A client initiates communication with a server by beginning a TCP connection on any 
mutually agreed TCP port, with the default being 6103 for unencrypted connections, and 
port 12109 for SSL-encrypted connections. When the TCP connection has entered the 
Established state, the session proceeds to the start of the Authorization phase.

14.2 Authorization

Authorization begins when the client sends the server a Login transaction. The Login 
transaction contains the basic information that the server requires in order to start an 
authorization decision: the user ID and optionally, some information about the client 
software.

A server responds to the Login request by sending back a “401 Unauthorized” status code 
and a WWW-Authenticate header. This is part of an authentication challenge to the client. 
Part of the WWW-Authenticate header may contain a checksum (nonce) of a 
concatenation of the following:

1 The client-IP.

2 The server-supplied timestamp.

3 The server’s private-key.

Server implementers should note that because of intervening proxy servers, the client IP 
address may change from connection to connection.

The client concatenates the nonce to the checksum of the Request-URI; then performs an 
MD5 digest using a concatenation of the username, realm and password as the secret. This 
result is then returned to the server as part of an Authorization header. The server MUST 

Version 1.7.2

  14-1

then compute the equivalent function using its own stored copy of the user’s password. If 
the two match and the nonce is the same, the user is considered authenticated, and the 
login can proceed with the server informing the client of the available capabilities. The 
login has been accomplished without actually sending the password. A server MAY 
provide an anonymous login. A client wishing an anonymous login sends an empty 
Authentication field in its Login transaction, after which the authorization proceeds as 
before.

14.3 Session

Once the Authorization phase has been completed, both endpoints enter the Session 
phase. During the Session phase, clients may issue any combination of requests for which 
they are authorized. The first of these MUST be to issue a GET requests for the “Action” 
URL, if any, included in the Login response (Section 4.10). After this, clients may issue 
other transactions.

Clients MAY issue multiple transactions without waiting for responses. However, servers 
are not required to process these requests in parallel, nor are servers required to complete 
the requests in the order in which they were issued. If a client issues a request before 
receiving a response to some earlier request, the client MUST be prepared to receive the 
responses in any order. The only way for a client to guarantee sequential execution of 
requests on every server is to wait for a response to any outstanding request before issuing 
a new request.

14.4 Termination

A client SHOULD initiate termination of the session by sending a Logoff transaction. If a 
server receives a Logoff transaction while other operations are pending, it SHOULD abort 
those pending operations. However, a server MUST NOT rely on receiving a Logoff 
transaction in order to terminate a session, due to the possibility of communications 
problems preventing the transmission of the Logoff transaction by the client.

Servers SHOULD provide a timeout mechanism, and if they do, MUST inform the client 
of the timeout interval during the Login transaction (Section 4.7). 

14-2  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0[DEPRECATED] SERVERINFORMATION 
TRANSACTION

The ServerInformation transaction allows retrieving global information about a server, or 
dynamic information about resources offered by a server.[deprecated]

15.1 Required Request Arguments

There are no required request arguments. A ServerInformation transaction with no 
request arguments requests global information.

15.2 Optional Request Arguments

Resource

Class

StandardNames

The name of the resource for which dynamic information is 
requested. This is interpreted as a SystemName unless the 
StandardNames argument is present and nonzero.

The name of the class within the resource for which dynamic 
information is requested. This is interpreted as a SystemName 
unless the StandardNames argument is present and nonzero.

A numeric value which, if zero, indicates that Resource and Class 
are both SystemName values, and which, if equal to 1, indicates 
that the Resource and Class names are both StandardName 
values.

15.3 Response Format

The response to the ServerInformation transaction is a well-formed XML document:

<RETS ReplyCode="replycode" ReplyText="replytext"> 
<ServerInformation> 
  <Parameter name="parametername" [resource="resourceID" 
[class="classID"]]> 
     value 
  </Parameter> 

Version 1.7.2

  15-1

</ServerInformation> 
</RETS>

NOTE

RETS 1.7.2 requires all server responses to be well-formed XML, and additionally requires 
ServerInformation transaction responses to be valid XML. In addition, RETS requires that clients parse 
server responses as XML, not as simple text streams. The response formats shown here are normative with 
respect to content, but not normative with respect to form. That is, servers are free to produce response 
XML in any format that complies with the W3C XML 1.0 recommendation, so long as it is valid with 
respect to the appropriate DTD. XML escaping of content is implied. See the W3C XML Recommendation 
1.0, Third Edition, for full information on XML.

The server MUST supply the information that applies to the Class level even if the 
information is global to the system. That is, the client is not required to infer information 
from the class hierarchy.

The well-known names for parameters are given in Table 15-1.

15.4 Well-known names

Table 15-1 lists the well-known names for parameters defined in this specification. Servers 
may extend this list, but MUST precede their parameter names with the string “X-”. 

Table 15-1Well-Known Parameter Names

Parameter

Level

Type

Description

CurrentTimeStamp System

DateTime

LastTimeStamp

ResourceClass

DateTime

MinimumLimit

ResourceClass

KeyLimit[depre-
cated]

ResourceClass

Numeric/
String

Numeric/
String

ReplicationSup-
port[deprecated]

Resource/Class

Character

The current system date and time, including the server 
time zone, in ISO 8601 format.

The most recent modification timestamp of any record in 
the given resource and class, in ISO 8601 format.

The minimum Limit value for any search in this class. 
the value NONE may be returned if there is no minimum 
limit.

The minimum Limit for any search in this class that 
includes a Key optional parameter. the value NONE may 
be returned if there is no minimum limit.[deprecated]

An indication of the level of replication support available 
for the given resource/class: 
N indicates that replication is not supported for this 
resource/class. 
Y indicates that replication is supported, that the server 
supports the optional Key search argument, and that all 
fields are marked as to their controlling timestamp or for-
eign key. A blank query may be used to retrieve all 
records that the user is permitted to access.
K indicates that replications is supported, and that the 
server supports the optional Key search argument. A 
query MUST contain one or more of the fields marked in 
the metadata with the KeyQuery flag.[deprecated]

15-2  Real Estate Transaction Specification

Version 1.7.2

15.5 Reply Codes

Table 15-2ServerInformation Reply Codes

Reply Code

Meaning

0
20601

20602

Operation successful.
Not supported. 
The transaction is not supported for the given resource and class.
Miscellaneous error. 
The transaction could not be completed. The ReplyText gives additional infor-
mation.

Version 1.7.2

  15-3

15-4  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0ACKNOWLEDGMENTS

The creation of this specification would not have been possible without the sponsorship 
and coordination of efforts provided by the National Association of REALTORS®.

This document has benefited greatly from the comments of all those participating in the 
National Association of REALTORS®-Standards Work Group.

In addition to the authors, valuable discussion instrumental in creating this document has 
come from:

Richard Mendenhall 
National Association of REALTORS®

Dale Stinton 
National Association of REALTORS®

Mark Lesswing 
National Association of REALTORS®

Larry Colson 
Moore Data Management Services

Tom Curtis 
Metro MLS

Kevin Knoepp 
GTE Enterprise Solutions

Tom McLean 
Resolution Software Consulting, Inc.

Tony Salvati 
Grant Thornton

Errol Samuelson 
RealSelect, Inc.

Allan Shapiro 
Wantao Zhou 
Interealty Corporation

Version 1.7.2

  16-1

Stuart Schuessler 
Libor Viktorin 
Mathew McGuire 
Steve Clarke 
MarketLinx Corporation

Michael DelGaudio 
MRIS, Inc.

Maggie Diaz 
Brita Brodin 
Laure Chipman 
WyldFyre, Inc.

Joshua Vosper 
Rapattoni Corporation

Laila Sharshar 
NewportWorks, Inc.

Eric Schlosser 
Hewlett-Packard Company

Frank Tadman 
MLSListings Inc.

Sergio Del Rio 
Templates for Business Inc.

Jaison Freed 
FBS Data Systems, Inc.

Ryan Bonham 
Transparent Technologies Inc.

Gina Accawi

Falcon Technologies Corp.

16-2  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0AUTHORS

Leo Bijnagte 
Vista Information Systems 
100 Washington Square, Suite 1000 
Minneapolis, MN 55401

Dan Musso 
WyldFyre Technologies, Inc. 
900 East Hamilton Ave. 
Suite 500 
Campbell, CA  95008

Bruce Toback 
OPT, Inc. 
11801 N. Tatum Blvd. 
Suite 142 
Phoenix, AZ  85028

Paul Stusiak 
Falcon Technologies Corporation. 
635 Ivy Ave.. 
Coquitlam, BC V3J 2H8

Email: pstusiak@falcontechnologies.com

Version 1.7.2

  17-1

17-2  Real Estate Transaction Specification

Version 1.7.2

S E C T I O N

CHAPTER 0REFERENCES

[1]

[2]

[3]

[4]

[5]

[6]

[7]

[8]

[9]

[10]

[11]

[12]

[13]

[14]

Braden, R., “Requirements for Internet Hosts — Communication Layers” STD 3, 
RFC 1123, IETF 1989.

Fielding, R., “Hypertext Transfer Protocol — Version 1.1”, RFC 2616, January 
1997

Rivest, R., “The MD5 Message Authentication Algorithm”, RFC 1321, April 1992

Crocker, D., “Standard for ARPA Internet Text Messages”, RFC 2822, IETF 2001

US-ASCII. Coded Character Set - 7-Bit American Standard Code for Information 
Interchange. Standard ANSI X3.4-1986, ANSI, 1986.

Franks, J., Hallam-Baker, P., Hostetler, J., Leach, P., Luotonen, A., Sink, E., and L. 
Stewart, “An Extension to HTTP: Digest Access Authentication”, RFC 2617, 
January 1997. 

International Organization for Standards, “Data Elements and Interchange 
Formats - Information Interchange - Representation of Dates and Times”, ISO 
8601, June 1988.

Borenstein, N., Freed, F., “Multipurpose Internet Mail Extensions (MIME) Part 
One: Format of Internet Message Bodies”, RFC 2045, November 1996.

American National Standard for Data Encryption Algorithm (DEA). Standard 
ANSI X3.92, ANSI, 1981.

Data Encryption Standard, FIPS46-2, December 30, 1993.

DES Modes of Operation, FIPS81, December 2, 1980

IEEE/ANSI Std. 1003.2-1992, Information Technology – Portable Operating 
System Interface (POSIX®) Part 2

Berners-Lee et al., “Uniform Resource Identifiers (URI): Generic Syntax”, 
RFC 2396, IETF 1998

Kaliski, “PKCS #7: Cryptographic Message Syntax Version 1.5”, RFC 2315, IETF 
1998

Version 1.7.2

  18-1

[15]

Kristol, D. and Montulli, L., “HTTP State Management Mechanism”, RFC 2109, 
IETF 1997

[16] W3C, “HTML 4.01 Specification”, W3C Recommendation 24 December 1999 

(http://www.w3.org/TR/html401/)

[17] W3C, “Extensible Markup Language (XML) 1.0 (Third Edition)”, W3C 

Recommendation 4 February 2004 (http://www.w3.org/TR/2004/REC-xml-
20040204/)

[18]

[19]

Rescorla, E., “HTTP Over TLS”, RFC 2818, May 2000

International Standards Organization, “ISO 8601:2004(E) Date elements and 
interchange formats - Information interchange - Representations of dates and 
times”

[20] W3C, “Date and Time Formats”, W3C Note 15 September 1997 [online] (http://

www.w3.org/TR/NOTE-datetime)

[21]

[22]

Klyne, G. and Newman, C., “Date and Time on the Internet: Timestamps”, 
RFC 3339, IETF 2002

Crocker, D. and Overell, P., “Augmented BNF for Syntax Specification: ABNF”, 
RFC 2234, IETF 1997

18-2  Real Estate Transaction Specification

Version 1.7.2

A P P E N D I X

APPENDIXDTD REFERENCES

Table A-1 DTD References

Real Estate Transaction Standard Data Content DTD

Description

The document returned by a search specifying STANDARD-XML format. 
This DTD describes the document only, not the entire response. It may be 
used when transmitting listing or membership data through a channel other 
than a RETS server (for example, FTP).
-//RETS//DTD RETS Data Content 1.7.2//EN
http://www.rets.org/dtd/2008/08/REData-20080829.dtd

Public Identifier
System Identifier
Real Estate Transaction Standard STANDARD-XML Search Response DTD

Description

The response returned by a search specifying STANDARD-XML format. This 
DTD simply encapsulates the REData DTD (above) in a standard RETS 
response element.
-//RETS//DTD RETS XML Search Response 1.7.2//EN
http://www.rets.org/dtd/2008/08/RETS-20080829.dtd

Public Identifier
System Identifier
Real Estate Transaction Standard COMPACT Search Response DTD

Description

Public Identifier
System Identifier

The response returned by a search specifying COMPACT or COMPACT-
DECODED format.
-//RETS//DTD RETS COMPACT Search Response 1.7.2//EN
http://www.rets.org

/dtd/2008/08/rets-compact-search-1_7_2.dtd

RETS Metadata Content DTD

Description

Public Identifier
System Identifier

This DTD describes the STANDARD-XML metadata format. It may be used 
when transmitting metadata through a channel other than a RETS server.
-//RETS//DTD Metadata Content 1.7.2//EN
http://www.rets.org

/dtd/2008/08/rets-metadata-content-1_7_2.dtd

RETS Metadata STANDARD-XML GetMetadata Response DTD

Description

Public Identifier
System Identifier

The document returned by a GetMetadata transaction specifying a format of 
STANDARD-XML. This encapsulates the RETS Metadata Content DTD in a 
standard RETS response element.
-//RETS//DTD Metadata 1.7.2//EN
http://www.rets.org

/dtd/2008/08/rets-metadata-1_7_2.dtd

Version 1.7.2

  A-1

Table A-1 DTD References

RETS Metadata COMPACT GetMetadata Response DTD

Description

Public Identifier
System Identifier

The document returned by a GetMetadata transaction specifying a format of 
COMPACT.
-//RETS//DTD Compact Metadata 1.7.2//EN
http://www.rets.org

/dtd/2008/08/rets-compact-metadata-1_7_2.dtd

RETS Login Response DTD

Description
Public Identifier
System Identifier

The document returned by a Login transaction.
-//RETS//DTD Login Response 1.7.2//EN
http://www.rets.org

/dtd/2008/08/rets-login-1_7_2.dtd

RETS Update Response DTD

Description
Public Identifier
System Identifier

The document returned by an Update transaction.
-//RETS//DTD Update 1.7.2//EN
http://www.rets.org

/dtd/2008/08/rets-update-1_7_2.dtd

Note Certain System Identifier values have been split across multiple lines to prevent 

hypenation characters being added to the document that are not part of the identifier. 
Each System Identifier is a well-formed URI.

A-2  Real Estate Transaction Specification

Version 1.7.2

A P P E N D I X

APPENDIXSAMPLE COMPACT METADATA RESPONSES

This appendix contains examples for COMPACT metadata responses. It is NON-
NORMATIVE: these examples illustrate one way of formatting COMPACT metadata, and 
one set of values. Section 11 describes the content and formatting rules in detail.

B.1 System

B.2 Resource

<METADATA-SYSTEM Version="1.00.000" Date="2002-03-20T12:03:38Z"> 
<SYSTEM SystemID= "NTREIS" SystemDescription= "North Texas Real Estate 
Information System" /> 
<COMMENTS> 
This is a comment line 
</COMMENTS> 
</METADATA-SYSTEM>

<METADATA-RESOURCE Version="1.00.000"  
Date="2002-03-20T12:03:38Z" > 

<COLUMNS>→ResourceID→StandardName→VisibleName→Description→ 

ClassCount→KeyField→ClassVersion→ClassDate→ObjectVersion→ 
ObjectDate→SearchHelpVersion→SearchHelpDate→EditMaskVersion→ 
EditMaskDate →LookupVersion→LookupDate→UpdateHelpVersion→ 
UpdateHelpDate →ValidationExpressionVersion→ 
ValidationExpressionDate→ValidationLookupVersion → 
ValidationLookupDate→ValidationExternalVersion→ 
ValidationExternalDate→</COLUMNS> 

<DATA>→Agent→Agent→ Agent→Agent Table→1→ Agentid→1.00.000→ 
2002-03-20T12:03:38Z→→→→→→→→→→→→→→→→→</DATA> 

<DATA>→Property→Property→Property→Property Tables→5→ 

LN→1.00.000→2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→</DATA> 

<DATA>→Tax→Tax→Tax→Multimedia objects→2→0→ PID→1.00.000→ 

Version 1.7.2

  B-1

2002-03-20T12:03:38Z→→→→→→→→→→→→→→→→→</DATA> 

</METADATA-RESOURCE>

B.3 Foreign Keys

<METADATA-FOREIGNKEYS Version="1.00.000000"  

Date="2002-01-23T12:37:38Z"> 

<COLUMNS>PARENT_RESOURCE_ID→PARENT_CLASS_ID→PARENT_SYSTEMNAME→ 
CHILD_RESOURCE_ID→CHILD_CLASS_ID→CHILD_SYSTEMNAME→</COLUMNS> 
<DATA>→Property→RES→MLSNUM→TAX→TAX→MLSNUM→</DATA> 
<DATA>→Property→RES→MLSNUM→History→History→MLSNUM→</DATA> 
<DATA>→Property→RES→MLSNUM→OpenHouse→OpenHouse→MLSNUM→</DATA> 
<DATA>→Property→RES→ListingAgentID→Agent→Agent→AgentID→</DATA> 
<DATA>→Property→RES→COListingAgentID→Agent→Agent→AgentID→</DATA> 
<DATA>→Property→RES→SellingAgentID→Agent→Agent→AgentID→</DATA> 
<DATA>→Property→RES→COSellingAgentIDvAgent→Agent→AgentID→</DATA> 
<DATA>→Property→RES→ListingOfficeID→Office→Office→OfficeID→</DATA> 
<DATA>→Property→RES→SellingOfficeID→Office→Office→OfficeID→</DATA> 
</METADATA-FOREIGNKEYS>

B.4 Class

GetMetadata request:

Type: METADATA-CLASS 
ID: 0

Compact reply:

<METADATA-CLASS Resource="Property" Version="1.00.000" 

Date="2002-03-20T12:03:38Z"> 

<COLUMNS>→ClassName→VisibleName→StandardName→Description→ 

TableVersion→TableDate→UpdateVersion →UpdateDate →</COLUMNS> 

<DATA>→RES→Single Family→Residential→ 
Single Family Residential→1.00.000→ 
2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→</DATA> 

<DATA>→CON→Condos→CommonInterest→Condos→1.00.000→ 

2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→</DATA> 

<DATA>→MUL→Multi Family→MultiFamily→ 
Multi Family Residential→1.00.000→ 
2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→</DATA> 

<DATA>→MOB→Mobile Home→ResidentialProperty→  

Mobile Homes→1.00.000→2002-03-20T12:03:38Z→ 
1.00.000→2002-03-20T12:03:38Z→</DATA> 

<DATA>→LND→Lots and Land→Lots and Land→Lots and Land→ 

1.00.000→2002-03-20T12:03:38Z→1.00.000→ 
2002-03-20T12:03:38Z→</DATA> 

</METADATA-CLASS> 
<METADATA-CLASS Resource="Agent" Version="1.00.000" 

Date="2002-03-20T12:03:38Z" /> 

<COLUMNS>→ClassName→VisibleName→StandardName→Description→ 

TableVersion→TableDate→UpdateVersion →UpdateDate →</COLUMNS> 

<DATA>→Agent→Agent→Agent→All Agents→1.00.000→ 

2002-03-20T12:03:38Z→→→</DATA> 

</METADATA-CLASS>

B-2  Real Estate Transaction Specification

Version 1.7.2

B.5 Table

GetMetadata request:

Type: METADATA-TABLE 
ID: Property: RES

Compact reply:

<METADATA-TABLE Resource="Property"  Class="RES" Version="1.00.000" 

Date= "2002-03-20T12:03:38Z" > 

<COLUMNS>→SystemName→StandardName→LongName→DBName→ShortName→ 

Maximumlength→DataType→Precision→Searchable→Interpretation→ 
Alignment→UseSeparator→EditMaskID→LookupName→MaxSelect→Units→ 
Index→Minimum→Maximum→Default→Required→SearchHelpID→ 
MetadataEntryID→ModTimeStamp→ForeignKey→ForeignField→KeyQuery→ 
KeySelect→</COLUMNS> 

<DATA>→LN→ListID→Listing ID→LN→ListID→8→Int→0→1→ 
Number→Left→0→→→→→1→→→1→→→→→→→→→</DATA> 

<DATA>→PTYP→PropType→Property Type→PT→Prop Type→ 

2→Int→0→1→Number→Left→0→→→→→→→→→→→→1→→→→→</DATA> 

<DATA>→LP→ListPrice→List Price→LP→Lst Pr→8→Int→0→1→ 

Currency→Right→1→→→→→14→→→2→→→→→1→→→→→</DATA> 

<DATA>→OWN→Owner→Owner Name→OWN→Own Name→20→Character→ 

0→0→→Left→0→→→→→→→→→→→</DATA> 

<DATA>→VEW→View→View→VEW→View→10→Long→0→1→LookupBitmask→Left→ 

0→→VEW→1→→→→→→→→→1→→→→→</DATA> 

<DATA>→EF→ExtFeat→Features→EF→Ext Feat→10→Character→0→1→ 

LookupMulti→Left→0→→EFT→2→→→→→→→→→→1→→→→→</DATA> 

<DATA>→SD→SchDist→School District→SD→SchDist→10→Character→ 
0→1→Lookup→Left→0→→SD→→→→→→→→→→→1→→→→→</DATA> 
<DATA>→AR→MLSArea→MLS Area→AR→Area→4→Int→0→1→Lookup→Left→ 

0→→AR→→→30→→→3→1→→→→1→→→→→</DATA> 

</METADATA-TABLE>

B.6 Update

GetMetadata request:

Type:METADATA-UPDATE 
ID: Property: RES 

Compact reply:

<METADATA-UPDATE Resource="Property" Class="RES" Version="1.00.000" 

Date= "2002-03-20T12:03:38Z" > 

<COLUMNS> →UpdateName→Description→KeyField→Version→Date→ 

MetadataEntryID→</COLUMNS> 

<DATA>→Add→Add a new Residential Listing→→1.00.000→ 

2002-03-20T12:03:38Z→→</DATA> 

<DATA>→Change→Change a Residential Listing→ListNumber→1.00.000→ 

2002-03-20T12:03:38Z→→</DATA> 

<DATA>→BOM→Put a Residential Listing Back on Market →ListNumber→ 

1.00.000→2002-03-20T12:03:38Z→→</DATA> 

</METADATA-UPDATE>

B.7 Update Type

GetMetadata request:

Version 1.7.2

  B-3

Type: METADATA-UPDATE_TYPE 
ID: Property: RES: Add

Compact reply:

<METADATA-UPDATE_TYPE Resource="Property" Class="RES" Update="Add" 

Version="1.00.000" Date="2002-03-20T12:03:38Z" > 
<COLUMNS>→SystemName→Sequence→Attributes→Default→ 

ValidationExpressionID→UpdateHelpID→ValidationLookupName→ 
ValidationExternalName→MetadataEntryID→MaxUpdate→</COLUMNS> 

<DATA>→STNUM→1→2→→→StNumHelp→→→→→</DATA> 
<DATA>→STNAME→2→2→→→→StreetName→→→→</DATA> 
<DATA>→LD→3→2→→ListDate→DateHelp→→→→→</DATA> 
<DATA>→LISTOFF→4→2,3→→→→→→</DATA> 
</METADATA-UPDATE_TYPE>

B.8 Object

GetMetadata request:

Class:METADATA-OBJECT 
ID:0

Compact reply:

<METADATA-OBJECT Resource="Property" Version="1.00.000" 

Date="2002-03-20T12:03:38Z" > 

<COLUMNS>→ObjectType→StandardName→VisibleName→Description→ 

MetadataEntryID→MIMEType→ObjectTimeStamp→ObjectCount→</COLUMNS> 

<DATA>→Photo→image→Full Photos→High Resolution Property Photos→ 

1→image/jpeg→PhotoTimestap→PhotoCount→</DATA> 
<DATA>→Thumbnail→image→Small Photos→Low  Resolution Property Photos→ 

1→image/jpeg→PhotoTimestap→PhotoCount→</DATA> 

</METADATA-OBJECT>

B.9 Lookup

GetMetadata request:

Type: METADATA-LOOKUP 
ID: 0

Compact reply:

<METADATA-LOOKUP Resource="Property" Version="1.00.000" 

Date="2002-03-20T12:03:38Z" > 

<COLUMNS>→LookupName→VisibleName→Version→Date→MetadataEntryID→</COLUMNS> 
<DATA>→1→Status→1.00.000→2002-03-20T12:03:38Z→</DATA> 
<DATA>→2→Phone Type→1.00.000→2002-03-20T12:03:38Z→</DATA> 
</METADATA-LOOKUP> 
<METADATA-LOOKUP Resource="Agent" Version="1.00.000" 

Date="2002-03-20T12:03:38Z"> 

<COLUMNS>→LookupName→VisibleName→Version→Date→MetadataEntryID→</COLUMNS> 
<DATA>→1→Status→1.00.000→2002-03-20T12:03:38Z→→</DATA> 
</METADATA-LOOKUP>

B.10 Lookup Type

GetMetadata request:

B-4  Real Estate Transaction Specification

Version 1.7.2

Type: METADATA-LOOKUP_TYPE 
ID: *

Compact reply:

<METADATA-LOOKUP_TYPE Resource="Property" Lookup="AR" Version="1.00.000" 

Date="2002-03-20T12:03:38Z"> 

><COLUMNS>→LongValue→ShortValue→Value→MetadataEntryID→</COLUMNS> 
<DATA>→Capitol Hill→Cap Hill→1→</DATA> 
<DATA>→Juanita  Hill→Juanita→2→→</DATA> 
<DATA>→Maple Valley→Mpl Valley→3→→</DATA> 
<DATA>→Downtown Redmond→Dntn Rdmd<4>→→</DATA> 
</METADATA-LOOKUP_TYPE> 
<METADATA-LOOKUP_TYPE Resource="Agent" Lookup="STAT" Version="1.00.000" 

Date= "2002-03-20T12:03:38Z"> 

<COLUMNS>→LongValue→ShortValue→Value→MetadataEntryID→</COLUMNS> 
<DATA>→Active →ACT→1→→</DATA> 
<DATA>→Suspended→SUS→2→→</DATA> 
<DATA>→Inactvie→INA→3→→</DATA> 
</METADATA-LOOKUP_TYPE>

B.11 Search Help

GetMetadata request:

Type: METADATA-SEARCH_HELP 
ID: Property

Compact reply:

<METADATA-SEARCH_HELP Resource="Property" Version="1.00.000" 

Date="2002-03-20T12:03:38Z" > 

<COLUMNS>→SearchHelpID→Value→MetadataEntryID→</COLUMNS> 
<DATA>→1→Enter the number in the following format dxd→→</DATA> 
<DATA>→2→Enter the number in the following format d.dd→→</DATA> 
</METADATA-SEARCH_HELP>

B.12 Edit Mask

GetMetadata request:

Type: METADATA-EDITMASK 
ID: Property 

Compact reply:

<METADATA-EDITMASK Resource="Property" Version="1.00.000" Date= "2002-03-
20T12:03:38Z"> 
<COLUMNS>→EditMaskID→Value→MetadataEntryID→</COLUMNS> 
<DATA>→1→[0-9]{1,2}[x][0-9]{1,2} →</DATA> 
<DATA>→2→[0-9]{3}-[0-9]{2}-[0-9}{4} →</DATA> 
</METADATA-EDITMASK>

B.13 Update Help

GetMetadata request:

Type: UPDATE_HELP 
ID: Property 

Compact reply:

Version 1.7.2

  B-5

<METADATA-UPDATE_HELP Resource="Property" Version="1.00.000" 

Date="2002-03-20T12:03:38Z" > 

<COLUMNS>→UpdateHelpID→Value→MetadataEntryID→</COLUMNS> 
<DATA>→1→Enter the number in the following format dxd→→</DATA> 
<DATA>→2→Enter the number in the following format d.dd→→</DATA> 
</METADATA-UPDATE_HELP>

B.14 Validation Lookup

GetMetadata request:

Type: METADATA-VALIDATION_LOOKUP 
ID: Property 

Compact reply:

<METADATA-VALIDATION_LOOKUP Resource="Property" Version="1.00.000" 

Date= "2002-03-20T12:03:38Z" > 

<COLUMNS>→ValidationLookupName→Parent1Field→ Parent2Field→ 

Version→Date→MetadataEntryID→</COLUMNS> 

<DATA>→School→Area→Subarea→1.00.000→2002-03-20T12:03:38Z→→ 

</DATA> 

<DATA>→ZipCode→Area→→1.00.000→2002-03-20T12:03:38Z→→</DATA> 
<DATA>→City→→→1.00.000→2002-03-20T12:03:38Z →→</DATA> 
</METADATA-VALIDATION_LOOKUP>

B.15 Validation Lookup Type

GetMetadata request:

Type: METADATA-VALIDATION_LOOKUP_TYPE 
ID: Property: School 

Compact reply:

<METADATA-VALIDATION_LOOKUP_TYPE Resource="Property" 
ValidationLookup="School" Version="1.00.000" 
Date="2002-03-20T12:03:38Z" > 

<COLUMNS>→ValidText→Parent1Value→ Parent2Value→MetadataEntryID→</COLUMNS> 
<DATA>→133→AREA1→SUBAREA1→→</DATA> 
<DATA>→134→AREA1→SUBAREA2→→</DATA> 
<DATA>→135→AREA2→→→</DATA> 
</METADATA-VALIDATION_LOOKUP_TYPE>

B.16 Validation Expression

GetMetadata request:

Type: METADATA-VALIDATION_EXPRESSION 
ID: Property 

Compact reply:

<METADATA-VALIDATION_EXPRESSION Resource="Property" Version="1.00.000" 

Date= "2002-03-20T12:03:38Z" > 

<COLUMNS>→ValidationExpressionID→ValidationExpressionType→Value→ 

MetadataEntryID→</COLUMNS> 

<DATA>→Office1→ACCEPT>→ 

 LAG=.AGENTCODE. .OR. (LO=.BROKERCODE. .AND. .ENTRY.=OFFICE)→→</DATA> 
<DATA>→Agent1→ACCEPT→(LAG=.AGENTCODE.) .OR. (SAG=.AGENTCODE.)→→</DATA> 

B-6  Real Estate Transaction Specification

Version 1.7.2

<DATA>→ListDate→ACCEPT→ LD>.TODAY. - 3 .AND. LD<.TODAY. + 3→→</DATA> 
</METADATA-VALIDATION_EXPRESSION>

B.17 Validation External

GetMetadata request:

Type: METADATA-VALIDATION_EXTERNAL 
ID: Property

Compact reply:

<METADATA-VALIDATION_EXTERNAL Resource="Property" Version="1.00.000" 

Date= "2002-03-20T12:03:38Z" > 

<COLUMNS>→ValidationExternalName→SearchResource→SearchClass→Version→Date→ 

MetadataEntryID→</COLUMNS> 

<DATA>→1→Office→ Office→1.00.000→2002-03-20T12:03:38Z→</DATA> 
<DATA>→2→Tax→HENN→1.00.000→2002-03-20T12:03:38Z→→</DATA> 
</METADATA-VALIDATION_EXTERNAL>

B.18 Validation External Type

GetMetadata request:

Type: METADATA-VALIDATION_EXTERNAL_TYPE 
ID: Property: VET1

Compact reply:

<METADATA-VALIDATION_EXTERNAL_TYPE Resource="Property" 

ValidationExternal="VET1" Version="1.00.000" 
Date="2002-03-20T12:03:38Z" > 

<COLUMNS>→SearchField→DisplayField→ResultsFields→MetadataEntryID→ 

</COLUMNS> 

<DATA>→AgentID, AgentCode→AgentName, OfficeName→SaleAgentID=AgentID, 

SaleAgentName=AgentName, SaleOfficeID=OfficeID, 
SaleOfficeName=OfficeName→→</DATA> 

</METADATA-VALIDATION_EXTERNAL_TYPE>

Version 1.7.2

  B-7

B-8  Real Estate Transaction Specification

Version 1.7.2

A P P E N D I X

APPENDIXSUMMARY OF RETS REPLY CODES

Table C-1 Consolidated list of RETS reply codes (Sheet 1 of 4)

Reply Code

Meaning

0
10000

20003

20004 thru 20011
20012

20013

20014 thru 20019
20022

20036

20037

20041

20050

20140

Operation successful
System error
The server has detected an error with the request that prevents it from 
identifying the type of request, or that prevents the server from routing the 
request for processing. This return code MUST NOT be used when a more 
specific return code can be determined.
Zero Balance
The user has zero balance left in their account.
RESERVED
Broker Code Required 
The user belongs to multiple broker codes and one must be supplied as part of 
the login. The broker list is sent back to the client as part of the login response 
(see section 4.6).
Broker Code Invalid 
The Broker Code sent by the client is not valid or not valid for the user
RESERVED
Additional login not permitted 
There is already a user logged in with this user name, and this server does not 
permit multiple logins.
Miscellaneous server login error 
The quoted-string of the body-start-line contains text that SHOULD be 
displayed to the user
Client authentication failed. 
The server requires the use of a client password (section 4.1.2), and the client 
either did not supply the correct client password or did not properly compute 
its challenge response value.
User-agent authentication required. 
The server requires the use of user-agent authentication (section 4.1.2), and 
the client did not supply the user-agent header values.
Server Temporarily Disabled 
The server is temporarily offline. The user should try again later
Insecure password. 
The password does not meet the site’s rules for password security.

Version 1.7.2

  C-1

Table C-1 Consolidated list of RETS reply codes (Sheet 2 of 4)

Reply Code

Meaning

20141

20142
20200

20201

20202

20203

20206

20207

20208

20209

20210

20211

20212 [deprecated]

20213 [deprecated]

20301
20302
20303
20311

20312

20400

20401

20402

20403

Same as Previous Password. 
The new password is the same as the old one.
The encrypted user name was invalid.
Unknown Query Field 
The query could not be understood due to an unknown field name.
No Records Found 
No matching records were found.
Invalid Select 
The Select statement contains field names that are not recognized by the 
server.
Miscellaneous Search Error 
The quoted-string of the body-start-line contains text that MAY be displayed 
to the user.
Invalid Query Syntax 
The query could not be understood due to a syntax error.
Unauthorized Query 
The query could not be executed because it refers to a field to which the 
supplied login does not grant access.
Maximum Records Exceeded 
Operation successful, but all of the records have not been returned. This reply 
code indicates that the maximum records allowed to be returned by the server 
have been exceeded. Note: reaching/exceeding the "Limit" value in the client 
request is not a cause for the server to generate this error.
Timeout 
The request timed out while executing
Too many outstanding queries 
The user has too many outstanding queries and new queries will not be 
accepted at this time.
Query too complex 
The query is too complex to be processed. For example, the query contains 
too many nesting levels or too many values for a lookup field.
Invalid key request [deprecated] 
The transaction does not meet the server’s requirements for the use of the 
Key option.
Invalid Key [deprecated] 
The transaction uses a key that is incorrect or is no longer valid. Servers are 
not required to detect all possible invalid key values.
Invalid parameter. Additional information is provided in the error block.
Unable to save record on server.
Miscellaneous Update Error.
WarningResponse was not given for all warnings that contained a 
response-required value of 2.
WarningResponse was given for a warning that contained a response-
required value of 0.
Invalid Resource 
The request could not be understood due to an unknown resource.
Invalid Type 
The request could not be understood due to an unknown object type for the 
resource.
Invalid Identifier 
The identifier does not match the KeyField of any data in the resource.
No Object Found 
No matching object was found to satisfy the request.

C-2  Real Estate Transaction Specification

Version 1.7.2

Table C-1 Consolidated list of RETS reply codes (Sheet 3 of 4)

Reply Code

Meaning

20406

20407

20408

20409

20410

20411

20412

20413

20500

20501

20502

20503

20506

20507

20508

20509

20510

20511

20512

20513

20514

Unsupported MIME type 
The server cannot return the object in any of the requested MIME types.
Unauthorized Retrieval 
The object could not be retrieved because it requests an object to which the 
supplied login does not grant access.
Resource Unavailable 
The requested resource is currently unavailable.
Object Unavailable 
The requested object is currently unavailable.
Request Too Large 
No further objects will be retrieved because a system limit was exceeded.
Timeout 
The request timed out while executing
Too many outstanding requests 
The user has too many outstanding requests and new requests will not be 
accepted at this time.
Miscellaneous error 
The server encountered an internal error.
Invalid Resource
The request could not be understood due to an unknown resource.
Invalid Type
The request could not be understood due to an unknown metadata type.
Invalid Identifier
The identifier is not known inside the specified resource.
No Metadata Found 
No matching metadata of the type requested was found.
Unsupported MIMEType
The server cannot return the metadata in any of the requested MIME types.
Unauthorized Retrieval
The metadata could not be retrieved because it requests metadata to which 
the supplied login does not grant access (e.g. Update Type data).
Resource Unavailable
The requested resource is currently unavailable.
Metadata Unavailable
The requested metadata is currently unavailable.
Request Too Large
Metadata could not be retrieved because a system limit was exceeded.
Timeout
The request timed out while executing.
Too many outstanding requests
The user has too many outstanding requests and new requests will not be 
accepted at this time.
Miscellaneous error
The server encountered an internal error.
Requested DTD version unavailable.
The client has requested the metadata in STANDARD-XML format using a 
DTD version that the server cannot provide.

Version 1.7.2

  C-3

Table C-1 Consolidated list of RETS reply codes (Sheet 4 of 4)

Reply Code

Meaning

20701

20702

Not logged in 
The server did not detect an active login for the session in which the Logout 
transaction was submitted.
Miscellaneous error. 
The transaction could not be completed. The ReplyText gives additional 
information.

C-4  Real Estate Transaction Specification

Version 1.7.2

A P P E N D I X

APPENDIXMAXIMUM FIELD LENGTH AND DISPLAY 
INFORMATION

This appendix contains examples for METADATA-TABLE Maximum Field Length and 
sample displays for various combinations of data types, interpretation and other attributes 
of a field. It is NON-NORMATIVE: these examples illustrate one case of calculating and 
formatting field values and their metadata and one set of values. Section 11.3.2 describes 
the rules in detail.

D.1 Datatype Boolean

Interpretation Precision Separator Units Max Select Extreme

Maximum

Display Example

null
null
Lookup

n/a
n/a
n/a

n/a
n/a
n/a

n/a n/a
n/a n/a
n/a n/a

Example
0
1
12345678

Length
1
1
8

False

True

from lookup 
LookupName, 
longvalue, 
lookup 
shortvalue, the 
value from the 
corresponding 
lookup values, 
from value, 0 
or 1

Version 1.7.2

  D-1

D.2 Datatype Character

Interpretation Precision Separator Units Max Select Extreme

Maximum

Display Example

null

Lookup

n/a

n/a

n/a

n/a

n/a n/a

Example
random_string 13

Length

n/a n/a

random_string 13

random_strin
g
from lookup 
LookupName, 
True

D.3 Datatype Decimal

Interpretation Precision Separator Units Max Select Extreme

Maximum

Display Example

Numeric
Numeric
Currency

2
1
2

,
,
,

null n/a
Feet n/a
n/a n/a

Example
-12342.21
123.1
1246.227

Length
9
5
7

-12,342.21

123.1 feet

$1246.22

D-2  Real Estate Transaction Specification

Version 1.7.2

A P P E N D I X

APPENDIXDOCUMENT REVISION HISTORY

This appendix contains the document revision history that identifies changes to the 
document. Such changes will be minor grammar, formating and spelling corrections and 
additional examples. Changes that modify the compliance suite for the standard or 
changes that add functionality are not reflected in this appendix. Those types of changes 
will be reflected in a new version number for the document.

Table E-1 Revision History

Author

Date
2008-08-29 P. Stusiak
2008-09-10 P. Stusiak

Sections
all
3, 5, 7, 11, 
12, 15

Notes
Release of 1.7.2
Correct the doctype url of section 7.6, page 
7-7, section 11.1.4, section 12 and section 
15 to that for 1.7.2

Version 1.7.2

  E-1

E-2  Real Estate Transaction Specification

Version 1.7.2

Index of Compliance Items

B
Backwards compatibility for XML metadata 2
Backwards compatibility in XML 4, 7

C
Cache-Control header 6
Classless searches, resource identifier 2
Compression options 7
cookies 1

E
end-reply-code on successful transaction 5

I
Interpretation of the LIMIT tag 4

L
Logoff 2
Logout transaction 1

M
Metadata extension names 2, 3, 4, 5
MIME type acceptance 1

Minimum requirements for compact-decoded for-
mat 2

P
Pending transactions at logoff 2

Q
Query parameter rounding 9

R

Representation  of  undefined  data  in  COMPACT 
format 1, 2
Requirement for search 5

S
Session timeout 5

T
TCP port for SSL connection 2

V
Version identifier usage 2

Version 1.7.2

  Real Estate Transaction Specification   IOC-1

IOC-2  Real Estate Transaction Specification

Version 1.7.2

Symbols
.ANY. token, 7-9
.EMPTY. token, 7-9

A
Accept-Encoding header, 3-7
account balance, 4-5
Accounting, 4-5

billing information, 6-1
logout, 6-1
agent code, 4-5
Authentication, 9-2, 14-1
Authorization, 4-2
example, 4-2

Auto-population, 10-1, 11-17

B
Body, response, 3-2
Broker Code, 4-2
Broker information, 4-2
in expressions, 4-6
in login, 4-3, 4-6

C
Cache-Control header, 3-5
Capability URL, 4-7
Case-sensitivity, 11-1
Change Password transaction, 9-1
Class

defined, 1-2
ClassName, 11-11
Client Authentication, 4-1
Client password, 3-3, 3-9
Compatibility, 1-2
Compliance, 1-1
compliance, 1-2, 10-4
compression, 3-7
Content-Type header, 3-6
Cookies, 3-1, 3-3, 3-7, 4-1
Count, retrieving, 7-3
cursor, 7-4

D
data types, 11-13
Date header, 3-5
dates

calculations, 11-26
format, 2-4
time zone, 7-9

defaults, required specification items, 1-2
Delimiters
field, 7-2

Index

E
ECB padding, 9-2
Edit Mask, 11-21
End reply code, 3-5
Error handling
GetObject, 5-6
multipart, 5-6
reporting, in update, 10-2, 10-3

Examples

update transaction, 10-1

Extending, 1-1

adding transactions, 4-7

Extensions

capability URL, 4-7
functions, 4-7

extensions, metadata, 11-2
External validation, 10-5

F
Field

selecting in search, 7-5

Field delimiter, 7-7
fields, restricting access to, 7-5
foreign keys, 11-8, B-2, D-2

G
GET transaction, 8-1

H
header

Accept-Encoding, 3-3, 3-7
Authorization, 3-3
Cache-Control, 3-5
Content-Length, 3-6
Content-Type, 3-6
Cookie, 3-3
Date, 3-5
Location, 5-4
RETS-Request-ID, 3-3, 3-7
RETS-Server, 3-7
RETS-UA-Authorization, 3-3
RETS-Version, 3-3, 3-6
server response, 3-7
Set-Cookie, 3-7
Transfer-Encoding, 3-6
User-Agent, 3-2

Headers

RETS version, 3-3

help text

search, 11-20
update, 11-22

HTTP

GET vs. Post, 3-2
Header usage, 3-2
method, 10-1
status code, usage, 3-4
Status codes, 3-8
update and, 10-1

Version 1.7.2

  Real Estate Transaction Specification  IX- 1

J
Justification, text, specifying, 11-14

K
KeyField, 11-6

L
literal string, 7-9
Login, 4-1
Logout, 6-1

M
MAXROWS, 7-4, 7-7
Message format, 3-1
Metadata

version control, 4-4
versioning, 4-3

metadata, 11-1
caching, 11-1
system, 11-4
version control, 11-1, 11-5, 11-6, 11-9, 11-10, 11-12, 11-16, 
11-17,  11-18,  11-19,  11-20,  11-21,  11-23,  11-24,  11-27, 
11-28, 11-29

Metadata extensions, 11-2
Metadata fields, unknown, 11-2
metadata, case-sensitivity, 11-1
MIME (Multimedia Internet Mail Extensions), 5-1

Multipart responses, 5-5

MIME Type, 5-1
multimedia

location, 5-3, 5-4

Multi-select

in update, 10-5
interpretation, 11-14

N
NOW, search token, 7-9

O
Object description, 5-4
Object ID, 5-2
Office list tag, 4-6
offset, in query, 7-4
Optional

defined, 1-3

P
Password expiration, 4-5
Passwords

expiration, 4-5

photos

location, 5-3, 5-4
object-ID, 5-2
Port number, 14-1
port number, 4-2

Q
Query

example, 7-10
field names in, 7-6
limiting records returned, 7-3
specification, 7-3

query

cursor, 7-4

query language, 7-8
qvalue, 5-2

R
record count, 7-2, 7-7
record limit, 7-3
regular expressions, 11-22
Reply code

at end of reply, 3-5

Request

arguments, 3-1
defined, 3-1
required headers, 3-2

Request format, 3-2
Request ID

defined, 1-3
in response, 3-7
transmitting, 3-3

Resource

defined, 1-3
standard names for, 11-5

Resource ID, 5-2
resources

well-known names, 11-5

Response, 3-2

general format, 3-4
RestrictedIndicator, 7-5
RETS status code, 3-4
RETS-Request-ID
header, 3-7

RETS-Server header, 3-7
RETS-Version header, 3-3, 3-6
rounding, in query computations, 7-9

S
Search

return format, 7-3
Search Help, 11-20
Search types, 7-1
Security, 4-1

controlling access to functions, 4-6

security

controlling access to fields, 7-5
password, 9-1
Server header, 3-7
ServerInformation transaction, 15-1
Set-Cookie header, 3-7
Sign-off message, 6-1
SSL, 4-1

IX-2  Real Estate Transaction Specification

Version 1.7.2

SSL (Secure Sockets Layer), 14-1
Standard name
defined, 1-3
syntax, 2-3

standard name, 7-6, 11-6
Standard-Name

searching with, 7-10

System Name
defined, 1-3
SystemName, 7-10

T
TCP port number, 14-1
Text justification, 11-14
Timeouts, 4-5
Timestamp

metadata, 4-3

TODAY, search token, 7-9
transaction

Change Password, 9-1
GET, 8-1
Update, 10-1

U
Update Help, 11-22

Update transaction, 10-1
Update warnings, 10-2
User class, 4-5
User information, 4-4
User level, 4-5
User-Agent, 3-3
User-Agent header, 3-2

V
Validation

external, 10-5, 11-17

validation, 10-4
validation expression, 11-25
Validation expressions, 10-5, 11-17
VisibleName, 11-6, 11-11

W
Warning block, 10-4
well-known names
login fields, 4-6
object types, 11-18
resources, 11-5
transactions, 4-7

Version 1.7.2

  Real Estate Transaction Specification   IX-3

IX-4  Real Estate Transaction Specification

Version 1.7.2

