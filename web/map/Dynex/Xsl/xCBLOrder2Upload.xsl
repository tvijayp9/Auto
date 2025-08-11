<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="UTF-8"/>
	<xsl:template match="/">
		<xsl:for-each select="EDXDATA">
			<EDXDATA>
				<xsl:for-each select="DOCDATA">
					<DOCDATA DESTEMAIL="False" DESTDESC="Quadrem via Axis" DESTPRINT="False" DESTPATH="False" DESTFAX="False">
						<xsl:attribute name="DESTID">
							<xsl:value-of select="BUSOBJ/Order/OrderHeader/OrderReferences/AccountCode/Reference/RefNum/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
						</xsl:attribute>
						<xsl:attribute name="DOCID">
							<xsl:value-of select="BUSOBJ/Order/OrderHeader/OrderNumber/SellerOrderNumber/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
						</xsl:attribute>
						<xsl:attribute name="DOCKEY">
							<xsl:value-of select="@DOCKEY"/>
						</xsl:attribute>
						<xsl:attribute name="DESTDESC">
							<xsl:value-of select="@DESTDESC"/>
						</xsl:attribute>
						<xsl:attribute name="EDXSENDMETH">TRSOUPLOAD_<xsl:value-of select="BUSOBJ/Order/OrderHeader/OrderReferences/AccountCode/Reference/RefNum/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
						</xsl:attribute>
						<xsl:for-each select="BUSOBJ/Order/OrderHeader">
							<SalesOrderHeader>
								<RecordTypeHeader FType="string" FLength="6" FAlign="left" FTrim="right" FDecimal="0">HEADER</RecordTypeHeader>
								<CustomerPO FType="string" FLength="20" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="OrderNumber/BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE"/>
								</CustomerPO>
								<CustomerCode FType="string" FLength="10" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="OrderReferences/AccountCode/Reference/RefNum/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</CustomerCode>
								<BuyLocation FLength="6"/>
								<RequiredDate FType="string" FLength="8" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="OrderDates/RequestedDeliverByDate/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</RequiredDate>
								<CustomerPODate FType="string" FLength="8" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="OrderIssueDate/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</CustomerPODate>
								<OverRideAddress FType="string" FLength="1" FAlign="left" FTrim="right" FDecimal="0">Y</OverRideAddress>
								<OverRideAddressName FType="string" FLength="35" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="OrderParty/ShipToParty/Party/NameAddress/Name1/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressName>
								<OverRideAddressLine1 FType="string" FLength="35" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="OrderParty/ShipToParty/Party/NameAddress/Name2/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressLine1>
								<OverRideAddressLine2 FLength="35" FAlign="left">
									<xsl:value-of select="OrderParty/ShipToParty/Party/NameAddress/Name3/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressLine2>
								<OverRideAddressLine3 FType="string" FLength="35" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="ListOfTransport/Transport/ShippingInstructions/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressLine3>
								<OverRideAddressCity>
									<xsl:value-of select="OrderParty/ShipToParty/Party/NameAddress/City/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressCity>
								<OverRideAddressCountryCode>
									<xsl:value-of select="OrderParty/ShipToParty/Party/NameAddress/Country/CountryCoded/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressCountryCode>
								<OverRideAddressPostCode>
									<xsl:value-of select="OrderParty/ShipToParty/Party/NameAddress/PostalCode/EDXBABLE/DESTINATION/CURRKEYVALUE"/>
								</OverRideAddressPostCode>
								<ShipLinesCompleted FType="string" FLength="1" FAlign="left" FTrim="right" FDecimal="0">N</ShipLinesCompleted>
								<CarrierCode FType="string" FLength="4" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="ListOfTransport/Transport/CarrierID/Identifier/Ident/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</CarrierCode>
								<CurrencyCode FType="string" FLength="3" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="OrderCurrency/Currency/CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE"/>
								</CurrencyCode>
								<OverRideAddressName2 FType="string" FLength="35" FAlign="left" FTrim="right" FDecimal="0"/>
								<Comment1 FType="string" FLength="35" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="ListOfStructuredNote/StructuredNote/GeneralNote/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</Comment1>
								<Comment2TradeRouteSaleOrderNumber FType="string" FLength="35" FAlign="left" FTrim="right" FDecimal="0">
									<xsl:value-of select="OrderNumber/SellerOrderNumber/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</Comment2TradeRouteSaleOrderNumber>
							</SalesOrderHeader>
						</xsl:for-each>
						<xsl:for-each select="BUSOBJ/Order/OrderDetail">
							<SalesOrderDetailS>
								<xsl:for-each select="ListOfItemDetail/ItemDetail">
									<DetailLine>
										<RecordTypeDetail FType="string" FLength="6" FAlign="left" FTrim="right" FDecimal="0">DETAIL</RecordTypeDetail>
										<CustomerPO FType="string" FLength="20" FAlign="left" FTrim="right" FDecimal="0">
											<xsl:value-of select="../../../OrderHeader/OrderNumber/BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE"/>
										</CustomerPO>
										<Item FType="string" FLength="30" FAlign="left" FTrim="right" FDecimal="0">
											<xsl:value-of select="BaseItemDetail/ItemIdentifiers/PartNumbers/SellerPartNumber/PartNum/PartID/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</Item>
										<UnitOfMeasure FType="string" FLength="4" FAlign="left" FTrim="right" FDecimal="0">
											<xsl:value-of select="BaseItemDetail/TotalQuantity/Quantity/UnitOfMeasurement/UOMCoded/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</UnitOfMeasure>
										<EndUser FType="string" FLength="30" FAlign="left" FTrim="right" FDecimal="0"/>
										<ItemDescription FType="string" FLength="50" FAlign="left" FTrim="right" FDecimal="0">
											<xsl:value-of select="BaseItemDetail/ItemIdentifiers/ItemDescription/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</ItemDescription>
										<UnitPrice FType="numeric" FLength="10" FAlign="right" FTrim="right" FDecimal="2">
											<xsl:value-of select="PricingDetail/ListOfPrice/Price/UnitPrice/UnitPriceValue/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</UnitPrice>
										<Quantity FType="numeric" FLength="11" FAlign="right" FTrim="right" FDecimal="2">
											<xsl:value-of select="BaseItemDetail/TotalQuantity/Quantity/QuantityValue/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</Quantity>
										<DeliveryDate FType="string" FLength="8" FAlign="left" FTrim="right" FDecimal="0">
											<xsl:value-of select="DeliveryDetail/ListOfScheduleLine/ScheduleLine/RequestedDeliveryDate/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</DeliveryDate>
										<CustPoLineNumber FType="string" FLength="6" FAlign="left" FTrim="right" FDecimal="0">
											<xsl:value-of select="BaseItemDetail/LineItemNum/BuyerLineItemNum/EDXBABLE/ORIGINATOR/VALUE"/>
										</CustPoLineNumber>
									</DetailLine>
								</xsl:for-each>
							</SalesOrderDetailS>
						</xsl:for-each>
					</DOCDATA>
				</xsl:for-each>
			</EDXDATA>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet><!-- Stylus Studio meta-information - (c)1998-2002 eXcelon Corp.
<metaInformation>
<scenarios ><scenario default="yes" name="Scenario2" userelativepaths="yes" externalpreview="yes" url="POAXIS2MSA_955550_20021218103019&#x2D;1.xml" htmlbaseurl="" processortype="internal" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/><scenario default="no" name="Scenario3" userelativepaths="yes" externalpreview="no" url="POAXIS2MSA_S00423_20021015101001&#x2D;1.xml" htmlbaseurl="" processortype="internal" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/></scenarios><MapperInfo srcSchemaPath="Full_order_schema_bablised_v1.xsd" srcSchemaRoot="EDXDATA" srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="xCBLOrder2Upload.xsd" destSchemaRoot="EDXDATA" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
</metaInformation>
-->