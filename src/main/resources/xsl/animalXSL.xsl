<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" omit-xml-declaration="yes"/>
	<xsl:template match="/">
		<html>
			<head>
				<meta charset="UTF-8"/>
				<title>Животные</title>
				<link rel="stylesheet" href="/static/style.css"/>
			</head>
			<body>
				<div class="header">
					<div class="header-link">
						<a href="/">Главная страница</a>
					</div>
					<div class="header-link">
						<a href="/xml/farmers" >Фермеры</a>
					</div>
					<div class="header-link current-link">
						<a href="/xml/animals">Животные</a>
					</div>
					<div class="header-link">
						<a href="/xml/animalTypes">Виды животных</a>
					</div>
				</div>
				<table border="1" style="margin-top: 5px">
					<tr bgcolor="#CCCCCC">
						<td><strong>Id</strong></td>
						<td><strong>Имя</strong></td>
						<td><strong>Вес</strong></td>
						<td><strong>Тип животного</strong></td>
						<td><strong>Фермер</strong></td>
					</tr>
					<xsl:for-each select="ArrayList/item">
						<tr>
							<td><xsl:value-of select="id"/></td>
							<td><xsl:value-of select="name"/></td>
							<td><xsl:value-of select="weight"/></td>
							<td><xsl:value-of select="animalType/name"/></td>
							<td>
								<xsl:value-of select="farmer/name"/>
								<xsl:text xml:space="preserve"> </xsl:text>
								<xsl:value-of select="farmer/surname"/>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>