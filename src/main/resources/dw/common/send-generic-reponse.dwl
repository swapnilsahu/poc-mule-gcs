/** DW Mapping for Generic Response */
%dw 2.0
output application/json
---
{
	code: "INFO",
	gcsResponse: payload
}