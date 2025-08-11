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
			<body>
				<table cellSpacing="5" width="100%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="0">
					<tbody>
						<tr>
							<td width="100%" style="border-right: 1px solid; border-top: 1px solid; border-left: 1px solid; border-bottom: 1px solid">
								<table cellSpacing="0" align="left" width="33%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>
										<tr>
											<td bgColor="#c0c0c0" width="50%" style="border-top: 1px solid; border-left: 1px solid" class="Hdrc">Customer:</td>
											<td bgColor="#c0c0c0" width="50%" style="border-right: 1px solid; border-top: 1px solid" class="Hdrh">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceParty/BuyerParty/Party/NameAddress/Name1/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td bgColor="#c0c0c0" width="50%" style="border-left: 1px solid" class="Hdrc">Invoice:</td>
											<td bgColor="#c0c0c0" width="50%" style="border-right: 1px solid" class="Hdrh">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceNumber/Reference/RefNum/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td bgColor="#c0c0c0" width="50%" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">Invoice date:</td>
											<td bgColor="#c0c0c0" width="50%" style="border-right: 1px solid; border-bottom: medium none" class="Hdrh">
												<xsl:call-template name="format-date">
													<xsl:with-param name="date" select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceIssueDate/EDXBABLE/ORIGINATOR/VALUE"/>
												</xsl:call-template>
												<xsl:text>&#xA;</xsl:text>
											</td>
										</tr>
										<tr>
											<td bgColor="#c0c0c0" width="50%" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">Invoice purpose:</td>
											<td bgColor="#c0c0c0" width="50%" style="border-right: 1px solid; border-bottom: medium none" class="Hdrh">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoicePurpose/InvoicePurposeCoded/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="50%" style="border-right: medium none; border-top: medium none; border-left: 1px solid; border-bottom: medium none" class="Hdrc">Cust. Order #:</td>
											<td width="50%" style="border-right: 1px solid; border-top: medium none; border-left: medium none; border-bottom: medium none" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceReferences/PurchaseOrderReference/PurchaseOrderNumber/Reference/RefNum/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="50%" style="border-right: medium none; border-top: medium none; border-left: 1px solid; border-bottom: medium none" class="Hdrc">Cust. Order Date:</td>
											<td width="50%" style="border-right: 1px solid; border-top: medium none; border-left: medium none; border-bottom: medium none" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceReferences/PurchaseOrderReference/PurchaseOrderDate/EDXBABLE/ORIGINATOR/VALUE"/>
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
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceParty/BuyerParty/Party/OrderContact/Contact/ContactName/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">Phone:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderHeader/OrderHeaderChanges/OrderHeader/OrderParty/BuyerParty/Party/OrderContact/Contact/ListOfContactNumber/ContactNumber/ContactNumberValue['TelephoneNumber'=following-sibling::ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">Fax:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderHeader/OrderHeaderChanges/OrderHeader/OrderParty/BuyerParty/Party/OrderContact/Contact/ListOfContactNumber/ContactNumber/ContactNumberValue['FaxNumber'=following-sibling::ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
										<tr>
											<td width="24%" class="Hdrc">Email:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderHeader/OrderHeaderChanges/OrderHeader/OrderParty/BuyerParty/Party/OrderContact/Contact/ListOfContactNumber/ContactNumber/ContactNumberValue['EmailAddress'=following-sibling::ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
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
											<td width="24%" class="Hdrc">Notes:</td>
											<td width="76%" class="Hdrc">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderHeader/OrderHeaderChanges/OrderHeader/ListOfTransport/Transport/ShippingInstructions/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
										</tr>
									</tbody>
								</table>
								<div align="center">
									<center>
										<table cellSpacing="0" width="30%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
											<tbody>
												<tr>
													<td width="100%" class="Hdrh" colSpan="2">ShipTo: </td>
												</tr>
												<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceParty/ShipToParty/Party/NameAddress/Name1/EDXBABLE/ORIGINATOR/VALUE"/>
													</td>
												</tr>
												<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceParty/ShipToParty/Party/NameAddress/Street/EDXBABLE/ORIGINATOR/VALUE"/>
													</td>
												</tr>
												<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceParty/ShipToParty/Party/NameAddress/City/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												</tr>
												
												<tr>
													<td width="100%" class="Hdrc" colSpan="2">
														<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceParty/ShipToParty/Party/NameAddress/Country/CountryCoded/EDXBABLE/ORIGINATOR/VALUE"/>
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
										<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceDetail/ListOfInvoiceItemDetail/InvoiceItemDetail">
											<tr>
												<td class="TblcSmall">
													<xsl:value-of select="InvoiceBaseItemDetail/ItemIdentifiers/PartNumbers/SellerPartNumber/PartNum/PartID/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td class="TblcSmall">
													<xsl:value-of select="InvoiceBaseItemDetail/ItemIdentifiers/ItemDescription/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td class="TblcSmall">
												<!--	<xsl:call-template name="format-date">
														<xsl:with-param name="date" select="DeliveryDetail/ListOfScheduleLine/ScheduleLine/RequestedDeliveryDate/EDXBABLE/ORIGINATOR/VALUE"/>
													</xsl:call-template>
													<xsl:text>&#xA;</xsl:text>-->
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="InvoiceBaseItemDetail/TotalQuantity/Quantity/QuantityValue/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="InvoicePricingDetail/ListOfPrice/Price/UnitPrice/UnitOfMeasurement/UOMCoded/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="format-number(InvoicePricingDetail/Tax/TaxableAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="format-number(InvoicePricingDetail/Tax/TaxAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="format-number(InvoicePricingDetail/TotalValue/MonetaryValue/MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
												</td>
											</tr>
											<tr>
												<td style="border-left: 1px solid" class="TblcSmall"/>
												<td bgColor="#e2e2e2" class="TblcSmall" colSpan="7">
													<xsl:text>General note : </xsl:text>
													
													
													
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
												<xsl:value-of select="format-number(EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/NetValue/MonetaryValue/MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
											</td>
										</tr>
										<tr>
											<td align="left" width="50%" class="TblhSmall">Total GST</td>
											<td align="right" width="50%" class="TblcSmall">
												<xsl:value-of select="format-number(EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/TotalTaxAmount/MonetaryValue/MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
											</td>
										</tr>
										<tr bgColor="#c0c0c0">
											<td align="left" width="50%" class="TblhSmall">Total</td>
											<td align="right" width="50%" class="TblcSmall">
												<xsl:value-of select="format-number(EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/GrossValue/MonetaryValue/MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
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
											<td bgColor="#c0c0c0" width="100%" class="Hdrh">Invoice notes</td>
										</tr>
										<tr>
											<td width="100%">
												<table cellSpacing="0" width="100%" border="0" borderColor="#111111" style="; border-collapse: collapse" class="TblcSmall" cellPadding="3">
													<tbody>
														<tr>
															<td width="100%">
																<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderHeader/OrderHeaderChanges/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote['ZINV'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
															</td>
														</tr>
														<tr>
															<td width="100%">
																<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderHeader/OrderHeaderChanges/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote['ZTC'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
															</td>
														</tr>
														<tr>
															<td width="100%">
																<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderHeader/OrderHeaderChanges/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote['ZMRK'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
															</td>
														</tr>
														<tr>
															<td width="100%">
																<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderHeader/OrderHeaderChanges/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote['ZGST'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
															</td>
														</tr>
														<tr>
															<td width="100%">
																<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderHeader/OrderHeaderChanges/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote['ZMSG'=following-sibling::NoteID/EDXBABLE/ORIGINATOR/VALUE]/EDXBABLE/ORIGINATOR/VALUE"/>
															</td>
														</tr>
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
<scenarios ><scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="..\..\Temp\PO_962684_20040723103844&#x2D;3.xml" htmlbaseurl="" outputurl="..\..\Temp\output.html" processortype="internal" profilemode="0" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/></scenarios><MapperInfo srcSchemaPath="" srcSchemaRoot="" srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
</metaInformation>
-->