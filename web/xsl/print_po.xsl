<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="Content-Language" content="en-us"/>
				<meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
				<style>
					<xsl:text>&lt;!--
.Hdrh{font-family:verdana; font-size:12px; font-weight:bold;}
.Hdrc{font-family:verdana; font-size:12px;}
.TblhSmall{font-family:verdana; font-size:10px; font-weight:bold;}
.TblcSmall{font-family:sans-serif; font-size:10px;}
--&gt;</xsl:text>
				</style>
				<title>TradeRoute Sales Order</title>
			</head>
			<body>- Organisation Identifier:
				<xsl:value-of select="EDXDATA/DOCDATA/TRADEROUTEVALUES/TRANSACTION_DESC"/>-
				<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/TempItemCoded/EDXBABLE/ORIGINATOR/VALUE"/>-
				<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/TempOrderResponseHeaderNote/EDXBABLE/ORIGINATOR/VALUE"/>

				<table cellSpacing="5" width="100%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="0">
					<tbody>
						<tr>
							<td width="100%" style="border-right: 1px solid; border-top: 1px solid; border-left: 1px solid; border-bottom: 1px solid">
								<table cellSpacing="0" align="left" width="33%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>
										<tr>
											<td bgColor="#c0c0c0" width="50%" style="border-top: 1px solid; border-left: 1px solid" class="Hdrc">Customer:</td>
											<td bgColor="#c0c0c0" width="50%" style="border-right: 1px solid; border-top: 1px solid" class="Hdrh">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Name1/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td bgColor="#c0c0c0" width="50%" style="border-left: 1px solid" class="Hdrc">Order:</td>
											<td bgColor="#c0c0c0" width="50%" style="border-right: 1px solid" class="Hdrh">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderNumber/BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td bgColor="#c0c0c0" width="50%" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">Order date:</td>
											<td bgColor="#c0c0c0" width="50%" style="border-right: 1px solid; border-bottom: medium none" class="Hdrh">
												<xsl:call-template name="format-date">
													<xsl:with-param name="date" select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderIssueDate/EDXBABLE/ORIGINATOR/VALUE"/>
												</xsl:call-template>
												<xsl:text>&#xA;</xsl:text>
											</td>
										</tr>
										<tr>
											<td bgColor="#c0c0c0" width="50%" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">Order purpose:</td>
											<td bgColor="#c0c0c0" width="50%" style="border-right: 1px solid; border-bottom: medium none" class="Hdrh">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/Purpose/PurposeCoded/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="50%" style="border-right: medium none; border-top: medium none; border-left: 1px solid; border-bottom: medium none" class="Hdrc">Cust. order #:</td>
											<td width="50%" style="border-right: 1px solid; border-top: medium none; border-left: medium none; border-bottom: medium none" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderNumber/BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="50%" style="border-right: medium none; border-top: medium none; border-left: 1px solid; border-bottom: medium none" class="Hdrc">Cust. ref:</td>
											<td width="50%" style="border-right: 1px solid; border-top: medium none; border-left: medium none; border-bottom: medium none" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderReferences/AccountCode/Reference/RefNum/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="50%" style="border-right: medium none; border-top: medium none; border-left: 1px solid; border-bottom: medium none" class="Hdrc">Cust. cost #:</td>
											<td width="50%" style="border-right: 1px solid; border-top: medium none; border-left: medium none; border-bottom: medium none" class="Hdrc"/>
										</tr>
										<tr>
											<td width="50%" style="border-right: medium none; border-top: medium none; border-left: 1px solid; border-bottom: 1px solid" class="Hdrc">Tax exempt #:</td>
											<td width="50%" style="border-right: 1px solid; border-top: medium none; border-left: medium none; border-bottom: 1px solid" class="Hdrc"/>
										</tr>
									</tbody>
								</table>
								<table cellSpacing="0" align="right" width="33%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>
										<tr>
											<td width="100%" class="Hdrh" colSpan="2">Contact details</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">Name:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/BuyerParty/Party/OrderContact/Contact/ContactName/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">Phone:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/BuyerParty/Party/OrderContact/Contact/ListOfContactNumber/ContactNumber/ContactNumberValue['TelephoneNumber'=following-sibling::ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">Fax:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/BuyerParty/Party/OrderContact/Contact/ListOfContactNumber/ContactNumber/ContactNumberValue['FaxNumber'=following-sibling::ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">Email:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/BuyerParty/Party/OrderContact/Contact/ListOfContactNumber/ContactNumber/ContactNumberValue['EmailAddress'=following-sibling::ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">
												<xsl:text/>
											</td>
											<td width="76%" class="Hdrc">
												<xsl:text/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">
												<xsl:text/>
											</td>
											<td width="76%" class="Hdrc">
												<xsl:text/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">Notes 1:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/ListOfTransport/Transport/ShippingInstructions/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">Notes 2:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">General Note:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderHeaderNote/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
									</tbody>
								</table>
								<div align="center">
									<center>
										<table cellSpacing="0" width="30%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
											<tbody>
												<tr>
													<td width="100%" class="Hdrh" colSpan="2">Delivery address</td>
												</tr>
												<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Name1/EDXBABLE/ORIGINATOR/VALUE"/>
													</td>
												</tr>
<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Name2/EDXBABLE/ORIGINATOR/VALUE"/>
													</td>
												</tr>
<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Name3/EDXBABLE/ORIGINATOR/VALUE"/>
													</td>
												</tr>


												<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Street/EDXBABLE/ORIGINATOR/VALUE"/>
													</td>
												</tr>
												<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/City/EDXBABLE/ORIGINATOR/VALUE"/>,
														<xsl:text><![CDATA[ ]]></xsl:text>
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/PostalCode/EDXBABLE/ORIGINATOR/VALUE"/>
													</td>
												</tr>
												<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Region/RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE"/>
													</td>
												</tr>
												<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Country/CountryCodedOther/EDXBABLE/ORIGINATOR/VALUE"/>
													</td>
												</tr>
												<tr>
													<td width="24%" class="Hdrc">
														<xsl:text/>
													</td>
													<td width="76%" class="Hdrc">
														<xsl:text/>
													</td>
												</tr>
												<tr>
													<td width="30%" class="Hdrc">Date required:</td>
													<td width="70%" class="Hdrh">
														<xsl:call-template name="format-date">
															<xsl:with-param name="date" select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderDetail/ListOfItemDetail/ItemDetail/DeliveryDetail/ListOfScheduleLine/ScheduleLine/RequestedDeliveryDate/EDXBABLE/ORIGINATOR/VALUE"/>
														</xsl:call-template>
														<xsl:text>&#xA;</xsl:text>
													</td>
												</tr>
											</tbody>
										</table>
									</center>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<table cellSpacing="5" width="100%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="0">
					<tbody>
						<tr>
							<td width="100%">
								<table cellSpacing="0" width="100%" border="1" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>
										<tr>
											<td bgColor="#c0c0c0" class="TblhSmall">Item #</td>
											<td bgColor="#c0c0c0" class="TblhSmall">Description</td>
											<td bgColor="#c0c0c0" class="TblhSmall">Date due</td>
											<td bgColor="#c0c0c0" class="TblhSmall">Quantity</td>
											<td bgColor="#c0c0c0" class="TblhSmall">UOM</td>
											<td bgColor="#c0c0c0" class="TblhSmall">Price</td>
											<td bgColor="#c0c0c0" class="TblhSmall">GST</td>
											<td bgColor="#c0c0c0" class="TblhSmall">Total</td>
										</tr>
										<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderDetail/ListOfItemDetail/ItemDetail">
											<tr>
												<td class="TblcSmall">
													<xsl:value-of select="BaseItemDetail/ItemIdentifiers/PartNumbers/SellerPartNumber/PartNum/PartID/EDXBABLE/ORIGINATOR/VALUE"/>
													<br></br>
													<xsl:value-of select="BaseItemDetail/ItemIdentifiers/PartNumbers/BuyerPartNumber/PartNum/PartID/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td class="TblcSmall">
													<xsl:value-of select="BaseItemDetail/ItemIdentifiers/ItemDescription/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td class="TblcSmall">
													<xsl:call-template name="format-date">
														<xsl:with-param name="date" select="DeliveryDetail/ListOfScheduleLine/ScheduleLine/RequestedDeliveryDate/EDXBABLE/ORIGINATOR/VALUE"/>
													</xsl:call-template>
													<xsl:text>&#xA;</xsl:text>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="BaseItemDetail/TotalQuantity/Quantity/QuantityValue/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="BaseItemDetail/TotalQuantity/Quantity/UnitOfMeasurement/UOMCoded/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="format-number(PricingDetail/ListOfPrice/Price/UnitPrice/UnitPriceValue/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:text>0.00</xsl:text>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="format-number(PricingDetail/TotalValue/MonetaryValue/MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
												</td>
											</tr>
											<tr>
												<td style="border-left: 1px solid" class="TblcSmall"/>
												<td bgColor="#e2e2e2" class="TblcSmall" colSpan="7">
													<xsl:text>Line Item Note: </xsl:text>
													<xsl:value-of select="LineItemNote/EDXBABLE/ORIGINATOR/VALUE"/>
													<br/>
													<xsl:text>General note 0: </xsl:text>
													<xsl:value-of select="ListOfStructuredNote/StructuredNote/GeneralNote['ZREF'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
													<br/>
													<xsl:text>General note 1: </xsl:text>
													<xsl:value-of select="ListOfStructuredNote/StructuredNote/GeneralNote['F01'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
													<br/>
													<xsl:text>Recipient: </xsl:text>
													<xsl:value-of select="BaseItemDetail/FinalRecipient/Party/NameAddress/Name1/EDXBABLE/ORIGINATOR/VALUE"/>
													<br/>
													<xsl:text>Unloading point: </xsl:text>
													<xsl:value-of select="BaseItemDetail/FinalRecipient/Party/NameAddress/Name3/EDXBABLE/ORIGINATOR/VALUE"/>
													<br/>
													<xsl:text>Department: </xsl:text>
													<xsl:value-of select="BaseItemDetail/FinalRecipient/Party/PartyID/Identifier/Ident/EDXBABLE/ORIGINATOR/VALUE"/>
													<br/>
													<xsl:text>Other Note : </xsl:text>
													<xsl:value-of select="ListOfStructuredNote/StructuredNote/GeneralNote['BEST'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
													<br/>
												</td>
											</tr>
										</xsl:for-each>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td width="100%">
								<table cellSpacing="0" align="right" border="1" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>
										<tr>
											<td align="left" width="50%" class="TblhSmall">Total ex GST</td>
											<td align="right" width="50%" class="TblcSmall">
												<xsl:value-of select="format-number(EDXDATA/DOCDATA/BUSOBJ/Order/OrderSummary/TotalAmount/MonetaryValue/MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
											</td>
										</tr>
										<tr>
											<td align="left" width="50%" class="TblhSmall">Total GST</td>
											<td align="right" width="50%" class="TblcSmall">
												<xsl:text>0.00</xsl:text>
											</td>
										</tr>
										<tr bgColor="#c0c0c0">
											<td align="left" width="50%" class="TblhSmall">Total</td>
											<td align="right" width="50%" class="TblcSmall">
												<xsl:value-of select="format-number(EDXDATA/DOCDATA/BUSOBJ/Order/OrderSummary/TotalAmount/MonetaryValue/MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td width="100%">
								<table cellSpacing="0" width="100%" border="1" borderColor="#111111" style="; border-collapse: collapse" cellPadding="5">
									<tbody>
										<tr>
											<td bgColor="#c0c0c0" width="100%" class="Hdrh">Delivery notes</td>
										</tr>
										<tr>
											<td width="100%">
												<table cellSpacing="0" width="100%" border="0" borderColor="#111111" style="; border-collapse: collapse" class="TblcSmall" cellPadding="3">
													<tbody>
<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/ListOfStructuredNote/StructuredNote">
															<tr>
																<td width="100%">
																	<xsl:value-of select="GeneralNote/EDXBABLE/ORIGINATOR/VALUE"></xsl:value-of>
																</td>
															</tr>
														</xsl:for-each>
														<!--<tr>
															<td width="100%">
																<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote['ZINV'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
															</td>
														</tr>
														<tr>
															<td width="100%">
																<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote['ZTC'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
															</td>
														</tr>
														<tr>
															<td width="100%">
																<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote['ZMRK'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
															</td>
														</tr>
														<tr>
															<td width="100%">
																<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote['ZGST'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
															</td>
														</tr>
														<tr>
															<td width="100%">
																<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote['ZMSG'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
															</td>
														</tr>-->
													</tbody>
												</table>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</body>
		</html>
	</xsl:template>

	<!-- 20030527T00:00:00+08:00 -->
	<xsl:template name="format-date">
		<xsl:param name="date"/>

		<xsl:variable name="day" select="substring($date, 7, 2)"/>
		<xsl:variable name="month" select="substring($date, 5, 2)"/>
		<xsl:variable name="year" select="substring($date, 1, 4)"/>

		<xsl:variable name="month2" select="substring(substring-after('01Jan02Feb03Mar04Apr05May06Jun07Jul08Aug09Sep10Oct11Nov12Dec', $month), 1, 3)"/>
		<xsl:value-of select="concat($day, '-', $month2, '-', $year)"/>
	</xsl:template>
</xsl:stylesheet><!-- Stylus Studio meta-information - (c)1998-2003. Sonic Software Corporation. All rights reserved.
<metaInformation>
<scenarios ><scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="20051013021731.xml" htmlbaseurl="" outputurl="..\..\Temp\output.html" processortype="internal" profilemode="0" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/></scenarios><MapperInfo srcSchemaPath="" srcSchemaRoot="" srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
</metaInformation>
-->