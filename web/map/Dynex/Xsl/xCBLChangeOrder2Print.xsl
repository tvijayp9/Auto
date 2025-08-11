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
							<xsl:value-of select="BUSOBJ/ChangeOrder/ChangeOrderHeader/OrderReference/Reference/RefNum/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
						</xsl:attribute>
						<xsl:attribute name="DOCID">
							<xsl:value-of select="BUSOBJ/Order/OrderHeader/OrderNumber/SellerOrderNumber/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
							<xsl:value-of select="BUSOBJ/ChangeOrder/ChangeOrderHeader/ChangeOrderNumber/BuyerChangeOrderNumber/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
						</xsl:attribute>
						<xsl:attribute name="EDXSENDMETH">TRSCOPRINT_<xsl:value-of select="BUSOBJ/ChangeOrder/ChangeOrderHeader/BuyerParty/Party/PartyID/Identifier/Ident/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
						</xsl:attribute>
						<xsl:attribute name="DOCKEY">
							<xsl:value-of select="@DOCKEY"/>
						</xsl:attribute>
						<xsl:attribute name="DESTDESC">
							<xsl:value-of select="@DESTDESC"/>
						</xsl:attribute>

						
						<xsl:for-each select="BUSOBJ/ChangeOrder/ChangeOrderHeader">
							<ChangeOrderHeader>
								<RecordTypeHeader FLength="50" FAlign="left">Header</RecordTypeHeader>
								<ChangeOrderNumber FLength="50" FAlign="left">
									<xsl:value-of select="ChangeOrderNumber/BuyerChangeOrderNumber/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</ChangeOrderNumber>
								<TRSChangeOrderNumber FLength="50" FAlign="left">
									<xsl:value-of select="ChangeOrderNumber/SellerChangeOrderNumber/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</TRSChangeOrderNumber>
								<TRSOriginalSaleOrderNumber FLength="50" FAlign="left">
									<xsl:value-of select="OriginalOrderHeader/OrderHeader/OrderNumber/SellerOrderNumber/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</TRSOriginalSaleOrderNumber>
								<CustomerPO FLength="50" FAlign="left">
									<xsl:value-of select="OrderReference/Reference/RefNum/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</CustomerPO>
								<CustomerCode FLength="50" FAlign="left">
									<xsl:value-of select="BuyerParty/Party/PartyID/Identifier/Ident/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</CustomerCode>
								<TransportMode FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/ListOfTransport/Transport/TransportMode/TransportModeCodedOther/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</TransportMode>
								<BuyLocation FLength="50" FAlign="left">
									<xsl:value-of select="BuyerParty/Party/PartyID/Identifier/Ident/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</BuyLocation>
								<RequiredDate FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/OrderDates/RequestedDeliverByDate/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</RequiredDate>
								<CustomerChangeOrderDate FLength="50" FAlign="left">
									<xsl:value-of select="ChangeOrderIssueDate/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</CustomerChangeOrderDate>
								<OverRideAddress FLength="50" FAlign="left">N</OverRideAddress>
								<OverRideAddressName FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Name1/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressName>
								<OverRideAddressLine1 FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Name2/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressLine1>
								<OverRideAddressLine2 FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Name3/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressLine2>
								<OverRideAddressLine3 FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/ListOfTransport/Transport/ShippingInstructions/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressLine3>
								<OverRideAddressCity FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/City/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressCity>
								<OverRideAddressCountryCode FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/Country/CountryCoded/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressCountryCode>
								<OverRideAddressPostCode FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/OrderParty/ShipToParty/Party/NameAddress/PostalCode/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</OverRideAddressPostCode>
								<CurrencyCode FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/OrderCurrency/Currency/CurrencyCoded/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</CurrencyCode>
								<Comment1 FLength="50" FAlign="left">
									<xsl:value-of select="OrderHeaderChanges/OrderHeader/ListOfStructuredNote/StructuredNote/GeneralNote/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
								</Comment1>
							</ChangeOrderHeader>
						</xsl:for-each>
						<xsl:for-each select="BUSOBJ/ChangeOrder/ChangeOrderDetail/ListOfChangeOrderItemDetail">
							<ChangeOrderDetailS FLength="50" FAlign="left">
								<xsl:for-each select="ChangeOrderItemDetail">
									<DetailLine FLength="50" FAlign="left">
										<ItemDetailChange FLength="50" FAlign="left">
											<xsl:value-of select="ItemDetailChangeCoded/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</ItemDetailChange>
										<ItemChange FLength="50" FAlign="left">
											<xsl:value-of select="ItemDetailChanges/ItemDetail/BaseItemDetail/ItemIdentifiers/PartNumbers/SellerPartNumber/PartNum/PartID/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</ItemChange>
										<UnitOfMeasure FLength="50" FAlign="left">
											<xsl:value-of select="ItemDetailChanges/ItemDetail/BaseItemDetail/TotalQuantity/Quantity/UnitOfMeasurement/UOMCoded/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</UnitOfMeasure>
										<UnitOfMeasureChange FLength="50" FAlign="left">
											<xsl:value-of select="OriginalItemDetail/ItemDetail/BaseItemDetail/TotalQuantity/Quantity/UnitOfMeasurement/UOMCoded/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</UnitOfMeasureChange>
										<CustomerPO FLength="50" FType="string" FAlign="left">
											<xsl:value-of select="../../../ChangeOrderHeader/OrderReference/Reference/RefNum/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</CustomerPO>
										<BuyerItem FLength="50" FAlign="left">
											<xsl:value-of select="ItemDetailChanges/ItemDetail/BaseItemDetail/ItemIdentifiers/PartNumbers/BuyerPartNumber/PartNum/PartID/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</BuyerItem>
										<Item FLength="50" FAlign="left">
											<xsl:value-of select="OriginalItemDetail/ItemDetail/BaseItemDetail/ItemIdentifiers/PartNumbers/SellerPartNumber/PartNum/PartID/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</Item>
										<ItemDescription FLength="50" FAlign="left">
											<xsl:value-of select="ItemDetailChanges/ItemDetail/BaseItemDetail/ItemIdentifiers/ItemDescription/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</ItemDescription>
										<ItemDescriptionChange FLength="50" FAlign="left">
											<xsl:value-of select="OriginalItemDetail/ItemDetail/BaseItemDetail/ItemIdentifiers/ItemDescription/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</ItemDescriptionChange>
										<UnitPrice FType="numeric" FLength="50" FAlign="left" FDecimal="2">
											<xsl:value-of select="ItemDetailChanges/ItemDetail/PricingDetail/ListOfPrice/Price/UnitPrice/UnitPriceValue/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</UnitPrice>
										<UnitPriceChange FType="Numeric" FLength="50" FAlign="left" FDecimal="2">
											<xsl:value-of select="OriginalItemDetail/ItemDetail/PricingDetail/ListOfPrice/Price/UnitPrice/UnitPriceValue/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</UnitPriceChange>
										<Quantity FType="numeric" FLength="50" FAlign="left">
											<xsl:value-of select="ItemDetailChanges/ItemDetail/BaseItemDetail/TotalQuantity/Quantity/QuantityValue/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</Quantity>
										<QuantityChange FType="numeric" FLength="50" FAlign="left">
											<xsl:value-of select="OriginalItemDetail/ItemDetail/BaseItemDetail/TotalQuantity/Quantity/QuantityValue/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</QuantityChange>
										<DeliveryDate FLength="50" FAlign="left" FTrim="right">
											<xsl:value-of select="ItemDetailChanges/ItemDetail/DeliveryDetail/ListOfScheduleLine/ScheduleLine/RequestedDeliveryDate/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</DeliveryDate>
										<DeliveryDateChange FLength="50" FAlign="left">
											<xsl:value-of select="OriginalItemDetail/ItemDetail/DeliveryDetail/ListOfScheduleLine/ScheduleLine/RequestedDeliveryDate/EDXBABLE/DESTINATION/RESOLVEDVALUE"/>
										</DeliveryDateChange>
										<CustPoLineNumber FLength="50" FAlign="left">
											<xsl:value-of select="OriginalItemDetail/ItemDetail/BaseItemDetail/LineItemNum/BuyerLineItemNum/EDXBABLE/ORIGINATOR/VALUE"/>
										</CustPoLineNumber>
									</DetailLine>
								</xsl:for-each>
							</ChangeOrderDetailS>
						</xsl:for-each>
					</DOCDATA>
				</xsl:for-each>
			</EDXDATA>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet><!-- Stylus Studio meta-information - (c)1998-2002 eXcelon Corp.
<metaInformation>
<scenarios ><scenario default="yes" name="Scenario2" userelativepaths="yes" externalpreview="no" url="COAXIS2MSA_9555470000_20021217125755&#x2D;1.xml" htmlbaseurl="" processortype="internal" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/><scenario default="no" name="Scenario3" userelativepaths="yes" externalpreview="no" url="POAXIS2MSA_S00423_20021015101001&#x2D;1.xml" htmlbaseurl="" processortype="internal" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/></scenarios><MapperInfo srcSchemaPath="Full_ChangeOrder_Bableised.xsd" srcSchemaRoot="EDXDATA" srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="xCBLChangeOrder2Print.xsd" destSchemaRoot="EDXDATA" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
</metaInformation>
-->