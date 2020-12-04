<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" omit-xml-declaration="yes"/>
	<xsl:template match="/">
		<html>		
			<body>
				<table border="1">
					<tr bgcolor="#CCCCCC">
						<td align="center"><strong>Id</strong></td>
						<td align="center"><strong>Имя</strong></td>
						<td align="center"><strong>Фамилия</strong></td>
					</tr>
					<xsl:for-each select="ArrayList/item">
						<tr>
							<td><xsl:value-of select="id"/></td>
							<td><xsl:value-of select="name"/></td>
							<td><xsl:value-of select="surname"/></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>	
</xsl:stylesheet>