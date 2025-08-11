<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="Content-Language" content="en-us"/>
				<meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
				<style>
					<xsl:text></xsl:text>
				</style>
				<title>Credit Note Request (CRA)</title>
			</head>
			<body>
				<table cellSpacing="5" width="100%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="0">
					<tbody>
						<tr>
								<td colspan="1" width="35%" align="left">
								<table style=" border-collapse: collapse;">
									<tbody>
									<tr>
									<td><font size="5">MSA (AUST) PTY LIMITED</font></td></tr>
									<tr><td><font size="1.8">INCORPORATED IN NSW A.C.N 000 389 A.B.N. 97 000 389 837</font></td></tr>
									<tr><td><font size="2">137 GILBA ROAD, GIRRAWEEN NSW 2145</font></td></tr>
									<tr><td><font size="2">PO BOX 43, WENTWORTHVILLE NSW 2145</font></td></tr>
									<tr><td><font size="2">PHONE: (02) 9688 0333</font></td></tr>
									<tr><td><font size="2">FAX: (02) 9696 1835</font></td></tr>
									</tbody>
									</table>
									</td>
							<td align="center" colspan="1" width="30%">
								<table cellSpacing="0" width="100%" border="0" cellPadding="2">
									<tbody>
										<tr><td align="center"><img src="../Program/MSALogo.gif"/></td>
										</tr>
										<tr>
											<td align="center" >
												<font size="5">CREDIT NOTE REQUEST</font>
											</td>
										</tr>
									</tbody>
								</table>
							
							</td>
							<td colspan="1" width="35%" align="right">
								<table style=" border-collapse: collapse;">
									<tbody>
										<xsl:choose>
											<xsl:when test="/EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/BranchCode/EDXBABLE/DESTINATION/RESOLVEDVALUE ='01 Sydney NSC'">
												<tr bgcolor="#c0c0c0">
													<td width="20%" style="border: 1px solid gray;">
														<img src="../Program/accept_16.gif"/>
													</td>
													<td width="80%" style="border: 1px solid gray;">01 Sydney NSC</td>
													
												</tr>
											</xsl:when>
											<xsl:otherwise>
												<tr>
													<td width="20%" style="border: 1px solid gray;">
														
													</td>
													<td width="80%" style="border: 1px solid gray;">01 Sydney NSC</td>
												
												</tr>
											</xsl:otherwise>
										</xsl:choose>
										<xsl:choose>
											<xsl:when test="/EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/BranchCode/EDXBABLE/DESTINATION/RESOLVEDVALUE ='01G Sydney Gir'">
												<tr bgcolor="#c0c0c0">
													<td width="20%" style="border: 1px solid gray;">
														<img src="../Program/accept_16.gif"/>
													</td>
													<td width="80%" style="border: 1px solid gray;">01G Sydney Gir</td>
												
												</tr>
											</xsl:when>
											<xsl:otherwise>
												<tr>
													<td width="20%" style="border: 1px solid gray;">
														
													</td>
													<td width="80%" style="border: 1px solid gray;">01G Sydney Gir</td>
												
												</tr>
											</xsl:otherwise>
										</xsl:choose>
										<xsl:choose>
											<xsl:when test="/EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/BranchCode/EDXBABLE/DESTINATION/RESOLVEDVALUE ='05 WA'">
												<tr bgcolor="#c0c0c0">
													<td width="20%" style="border: 1px solid gray;">
														<img src="../Program/accept_16.gif"/>
													</td>
													<td width="80%" style="border: 1px solid gray;">05 Western Australia</td>
											
												</tr>
											</xsl:when>
											<xsl:otherwise>
												<tr>
													<td width="20%" style="border: 1px solid gray;">
														
													</td>
													<td width="80%" style="border: 1px solid gray;">05 Western Australia</td>
												
												</tr>
											</xsl:otherwise>
										</xsl:choose>
										<xsl:choose>
											<xsl:when test="/EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/BranchCode/EDXBABLE/DESTINATION/RESOLVEDVALUE ='08 NZ'">
												<tr bgcolor="#c0c0c0">
													<td width="20%" style="border: 1px solid gray;">
														<img src="../Program/accept_16.gif"/>
													</td>
													<td width="80%" style="border: 1px solid gray;">08 New Zealand</td>
													
												</tr>
											</xsl:when>
											<xsl:otherwise>

												<tr>
													<td width="20%" style="border: 1px solid gray;">
														
													</td>
													<td width="80%" style="border: 1px solid gray;">08 New Zealand</td>
													
												</tr>
											</xsl:otherwise>
										</xsl:choose>
									</tbody>
								</table>
							</td>
						</tr>
					
						<tr>
							<td colspan="3">
								<table cellSpacing="0" align="left" width="100%" border="0" cellPadding="2">
									<tbody>
										<br/>
										<tr>
											<td width="25%" class="Hdrc">Date: <xsl:call-template name="format-date">
													<xsl:with-param name="date" select="substring(EDXDATA/DOCDATA/TRADEROUTEVALUES/TRANSACTION_ID,1,8)"/></xsl:call-template></td>
											<td width="25%" class="Hdrc">RGA No: <xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/RGANO/EDXBABLE/DESTINATION/RESOLVEDVALUE"/></td>
											<td width="25%" class="Hdrc">Receipt No: <xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/RecNO/EDXBABLE/DESTINATION/RESOLVEDVALUE"/></td>
											<td width="25%" class="Hdrc">CRA No: <xsl:value-of select="EDXDATA/DOCDATA/TRADEROUTEVALUES/TRANSACTION_NUMBER"/></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="3" style="border-right: 1px solid; border-top: 1px solid; border-left: 1px solid; border-bottom: 1px solid">
								<table cellSpacing="0" align="left" width="100%" border="1" borderColor="#111111" style="; border-collapse: collapse" cellPadding="2">
									<tbody>
										<tr>
											<td width="25%" bgColor="#c0c0c0" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">Customer Name:</td>
											<td width="25%" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">Client No:<xsl:value-of select="/EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceParty/BuyerParty/Party/PartyID/Identifier/Ident/text()"/></td>
											<td width="25%" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">Order No:<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceReferences/PurchaseOrderReference/PurchaseOrderNumber/Reference/RefNum/EDXBABLE/ORIGINATOR/VALUE"/></td>
											<td width="25%" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">Invoice No:<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceNumber/Reference/RefNum/EDXBABLE/ORIGINATOR/VALUE"/></td>
										</tr>
										<tr>
											<td width="25%" style="border-right: 1px solid; border-bottom: medium none" class="Hdrh">
												<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceParty/BuyerParty/Party/NameAddress/Name1/EDXBABLE/ORIGINATOR/VALUE"/>
											</td>
											<td width="25%" style="border-right: 1px solid; border-bottom: medium none" class="Hdrh">Territory Code :<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceReferences/OtherInvoiceReferences/ListOfReferenceCoded/ReferenceCoded/PrimaryReference/Reference/RefNum/EDXBABLE/ORIGINATOR/VALUE"/></td>
											<td width="25%" style="border-right: 1px solid; border-bottom: medium none" class="Hdrh">Invoice Date :<xsl:call-template name="format-date">
													<xsl:with-param name="date" select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceIssueDate/EDXBABLE/ORIGINATOR/VALUE"/></xsl:call-template></td>
											<td width="25%" style="border-right: 1px solid; border-bottom: medium none" class="Hdrh">Delivery Docket :<xsl:value-of select="/EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/InvoiceReferences/SupplierOrderNumber/Reference/RefNum/text()"/></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<table cellSpacing="0" align="left" width="100%" border="0" cellPadding="2">
									<tbody>
										<tr>
											<td>Credit Note Request Reason : <xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/ReasonCode/EDXBABLE/DESTINATION/RESOLVEDVALUE"/></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				<table cellSpacing="5" width="100%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="0">
					<tbody>
						<tr>
							<td width="100%" >
								<table cellSpacing="0" width="100%" border="1" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>
										<tr>
											<td bgColor="#c0c0c0" class="TblhSmall">Item #</td>
											<td bgColor="#c0c0c0" class="TblhSmall">Description</td>
											<td bgColor="#c0c0c0" align="right" class="TblhSmall">Unit Price</td>
											<td bgColor="#c0c0c0" align="right" class="TblhSmall">Quantity</td>
											<td bgColor="#c0c0c0" align="right" class="TblhSmall">UOM</td>
											<td bgColor="#c0c0c0" align="right" class="TblhSmall">Disc%</td>
											<td bgColor="#c0c0c0" align="right" class="TblhSmall">Sub Total</td>
											<td bgColor="#c0c0c0" align="right" class="TblhSmall">CR QTY</td>
											<td bgColor="#c0c0c0" align="right" class="TblhSmall">CR Price</td>
											<td bgColor="#c0c0c0" align="right" class="TblhSmall">CR Disc%</td>
											<td bgColor="#c0c0c0" align="right" class="TblhSmall">Net Diff</td>
											<td bgColor="#c0c0c0" align="right" class="TblhSmall">CR Ext Total</td>
										</tr>

										<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceDetail/ListOfInvoiceItemDetail/InvoiceItemDetail">
										<xsl:if test="CorrectedExt/EDXBABLE/DESTINATION/RESOLVEDVALUE > 0 ">
											<tr>
												<td class="TblcSmall">
													<xsl:value-of select="InvoiceBaseItemDetail/ItemIdentifiers/PartNumbers/SellerPartNumber/PartNum/PartID/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td class="TblcSmall">
													<xsl:value-of select="InvoiceBaseItemDetail/ItemIdentifiers/ItemDescription/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>

												<td align="right" class="TblcSmall">
													<xsl:value-of select="InvoicePricingDetail/ListOfPrice/Price/UnitPrice/UnitPriceValue/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="InvoiceBaseItemDetail/TotalQuantity/Quantity/QuantityValue/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="InvoicePricingDetail/ListOfPrice/Price/UnitPrice/UnitOfMeasurement/UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<!--<xsl:value-of select="format-number(InvoicePricingDetail/Tax/TaxAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>-->
													<xsl:value-of select="InvoicePricingDetail/ListOfPrice/Price/PriceMultiplier/Multiplier/EDXBABLE/ORIGINATOR/VALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="format-number(InvoicePricingDetail/Tax/TaxableAmount/EDXBABLE/ORIGINATOR/VALUE, &quot;###,###.00&quot;)"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="CorrectedQty/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="CorrectedPrice/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="Discount/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="NetDiff/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="CorrectedExt/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
												</td>
											</tr>
											</xsl:if>
										</xsl:for-each>
									</tbody>
								</table>
							</td>
						</tr>
						<xsl:if test="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/ChargeTotal/MonetaryValue/MonetaryAmount/EDXBABLE/DESTINATION/RESOLVEDVALUE !='' ">
							<tr>
								<td width="100%">
									<table cellSpacing="0" width="100%" border="1" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
										<tbody>
											<tr>
												<td bgColor="#c0c0c0" class="TblhSmall">Description</td>
												<td bgColor="#c0c0c0" align="right" class="TblhSmall">Original Freight Charges</td>
												<td bgColor="#c0c0c0" align="right" class="TblhSmall">Corrected Freight Charges</td>
											</tr>

											<tr>
												<td class="TblcSmall">Freight Charges</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="format-number(EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/ChargeTotal/MonetaryValue/MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE, '0.00')"/>
												</td>
												<td align="right" class="TblcSmall">
													<xsl:value-of select="format-number(EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/ChargeTotal/MonetaryValue/MonetaryAmount/EDXBABLE/DESTINATION/RESOLVEDVALUE, '0.00')"/>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</xsl:if>
						<tr>
							<td width="100%">
								<table cellSpacing="0" align="right" border="1" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>
										<tr>

											<xsl:variable name="percentage"><xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/ReStockingFee/EDXBABLE/DESTINATION/RESOLVEDVALUE"/></xsl:variable>
											<xsl:variable name="value">	<xsl:value-of select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/ReStockingFee/EDXBABLE/DESTINATION/VALIDATEDOK"/></xsl:variable>
											<xsl:choose>
												<xsl:when test="number($value)">
													<td align="left" width="50%" class="TblhSmall">
													 ReStocking Fee	(<xsl:value-of select="$percentage"/> %)</td>
													<td align="right" width="50%" class="TblcSmall">$ 
														<xsl:value-of select="$value"/>											
													</td>
														</xsl:when>
												<xsl:otherwise>
													<td align="left" width="50%" class="TblhSmall">
													 ReStocking Fee	(0 %)</td>
													<td align="right" width="50%" class="TblcSmall">
														$ 0.00										
													</td>
												</xsl:otherwise>
										</xsl:choose>
											
										</tr>
										<tr>
											<td align="left" width="50%" class="TblhSmall">Total ex GST</td>
											<td align="right" width="50%" class="TblcSmall">$ 
												<xsl:value-of select="format-number(EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/NetValue/MonetaryValue/MonetaryAmount/EDXBABLE/DESTINATION/RESOLVEDVALUE, &quot;###,###.00&quot;)"/>
											</td>
										</tr>
										<tr>
											<td align="left" width="50%" class="TblhSmall">Total GST</td>
											<td align="right" width="50%" class="TblcSmall">$ 
												<xsl:value-of select="format-number(EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/TotalTaxAmount/MonetaryValue/MonetaryAmount/EDXBABLE/DESTINATION/RESOLVEDVALUE, &quot;###,###.00&quot;)"/>
											</td>
										</tr>
										<tr bgColor="#c0c0c0">
											<td align="left" width="50%" class="TblhSmall">Total</td>
											<td align="right" width="50%" class="TblcSmall">$ 
												<xsl:value-of select="format-number(EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceSummary/InvoiceTotals/GrossValue/MonetaryValue/MonetaryAmount/EDXBABLE/DESTINATION/RESOLVEDVALUE, &quot;###,###.00&quot;)"/>
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
											<td bgColor="#c0c0c0" width="100%" class="Hdrh">Credit Note Request Comments</td>
										</tr>
										<tr>
											<td width="100%">
												<table cellSpacing="0" width="100%" border="1" borderColor="#111111" style="; border-collapse: collapse" class="TblcSmall" cellPadding="3">
													<tbody>
														<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/Invoice/InvoiceHeader/ListOfStructuredNote/StructuredNote">
															<tr>
																<td width="20%">
																	<xsl:value-of select="NoteID"/>
																</td>
																<td width="80%">
																	<xsl:value-of select="GeneralNote"/>
																</td>
															</tr>
														</xsl:for-each>
													</tbody>
												</table>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table cellSpacing="0" align="left" width="33%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>
										<tr>
											<td width="50%" style="border-top: 1px solid; border-left: 1px solid; border-bottom: 1px solid" class="Hdrc">AUTHORISED BY:</td>
											<td width="50%" style=" border-right: 1px solid; border-top: 1px solid; border-bottom: 1px solid">
												<xsl:value-of select="EDXDATA/DOCDATA/TRADEROUTEVALUES/USER_LIST"/>
											</td>
										</tr>
										<tr>
											<td width="50%" style=" border-top: 1px solid;border-left: 1px solid; border-bottom: 1px solid" class="Hdrc">RAISED BY:</td>
											<td width="50%" style=" border-right: 1px solid; border-top: 1px solid; border-bottom: 1px solid">
												<xsl:value-of select="EDXDATA/DOCDATA/TRADEROUTEVALUES/CREATOR_BRANCH"/>
											</td>
										</tr>
									</tbody>
								</table>

								<table cellSpacing="0" align="left" width="67%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>

										<tr>
											<td width="50%"></td>
											<td width="50%" align="right">.</td>
										</tr>
										<tr>
											<td width="50%"></td>
											<td width="50%" align="right">
												<font size="5" face="bold">ORIGINAL</font>
											</td>
										</tr>
									</tbody>
								</table>
								<!--<table cellSpacing="0" align="left"  width="33%" border="1" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>
										<tr>
											<td width="100%" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">RECEIVED BY: </td>
											
										</tr>
										<tr>
											<td width="100%" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">LOT / BATCH RECORDS: </td>
											</tr>
											<tr>
											<td width="50%" style="border-left: 1px solid; border-bottom: medium none" class="Hdrc">INVENTORY RECORDS: </td>
											</tr>
									</tbody>
								</table>
								<table cellSpacing="0" align="left"  width="34%" border="0" borderColor="#111111" style="; border-collapse: collapse" cellPadding="3">
									<tbody>
										<tr>
											<td width="40%" class="Hdrc">DISTRIBUTION:</td>
											<td width="60%" >WHITE: CUSTOMER, QC</td>
										</tr>
										<tr>
											<td width="40%" class="Hdrc"></td>
											<td width="60%" >YELLOW:    RETENSION</td>
										</tr>
									</tbody>
								</table>-->
							</td>
							<!--</td>-->
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
<scenarios ><scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="INV_1634624_20060807044404&#x2D;1.xml_Resolver" htmlbaseurl="" outputurl="..\..\Temp\output.html" processortype="internal" profilemode="0" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/></scenarios><MapperInfo srcSchemaPath="" srcSchemaRoot="" srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
</metaInformation>
-->