<?xml version="1.0" encoding="UTF-8"?>
<!--Version 1.1 -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<OrderResponse xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="OrderResponse.xsd" xsi:schemaLocation="OrderResponse.xsd file://c:\EDX\SRC\TradeRoute\transformations\MincomMSA\mappings\OrderResponse.xsd">
      <!--OrderResponse xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="OrderResponse.xsd" xsi:schemaLocation="OrderResponse.xsd file://c:\Dynex\Xsd\xCBL30\singleRoot\OrderResponse.xsd"-->
			<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderHeader">
				<OrderResponseHeader>
					<xsl:for-each select="OrderNumber">
						<OrderResponseNumber>
							<xsl:for-each select="BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
								<BuyerOrderResponseNumber>
									<xsl:value-of select="."/>
								</BuyerOrderResponseNumber>
							</xsl:for-each>
							<SellerOrderResponseNumber></SellerOrderResponseNumber>
						</OrderResponseNumber>
					</xsl:for-each>
					<OrderResponseIssueDate>20020404T00:00:00+10:00</OrderResponseIssueDate>
					<OrderResponseDocTypeCoded>OrderResponse</OrderResponseDocTypeCoded>
					<OrderReference>
						<Reference>
							<xsl:for-each select="OrderNumber/BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
								<RefNum>
									<xsl:value-of select="."/>
								</RefNum>
							</xsl:for-each>
							<xsl:for-each select="OrderIssueDate/EDXBABLE/ORIGINATOR/VALUE">
								<RefDate>
									<xsl:value-of select="."/>
								</RefDate>
							</xsl:for-each>
						</Reference>
					</OrderReference>
					<xsl:for-each select="OrderParty/SellerParty">
						<SellerParty>
							<xsl:for-each select="Party">
								<Party>
									<xsl:for-each select="PartyID">
										<PartyID>
											<xsl:for-each select="Identifier">
												<Identifier>
													<xsl:for-each select="Agency">
														<Agency>
															<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																<AgencyCoded>
																	<xsl:value-of select="."/>
																</AgencyCoded>
															</xsl:for-each>
															<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																<AgencyCodedOther>
																	<xsl:value-of select="."/>
																</AgencyCodedOther>
															</xsl:for-each>
														</Agency>
													</xsl:for-each>
													<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
														<Ident>
															<xsl:value-of select="."/>
														</Ident>
													</xsl:for-each>
												</Identifier>
											</xsl:for-each>
										</PartyID>
									</xsl:for-each>
									<xsl:for-each select="ListOfIdentifier">
										<ListOfIdentifier>
											<xsl:for-each select="Identifier">
												<Identifier>
													<Agency>
														<xsl:for-each select="Agency/AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
															<AgencyCoded>
																<xsl:value-of select="."/>
															</AgencyCoded>
														</xsl:for-each>
														<xsl:for-each select="Agency/AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
															<AgencyCodedOther>
																<xsl:value-of select="."/>
															</AgencyCodedOther>
														</xsl:for-each>
													</Agency>
													<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
														<Ident>
															<xsl:value-of select="."/>
														</Ident>
													</xsl:for-each>
												</Identifier>
											</xsl:for-each>
										</ListOfIdentifier>
									</xsl:for-each>
									<NameAddress>
										<xsl:for-each select="NameAddress/Name1/EDXBABLE/ORIGINATOR/VALUE">
											<Name1>
												<xsl:value-of select="."/>
											</Name1>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/Name2/EDXBABLE/ORIGINATOR/VALUE">
											<Name2>
												<xsl:value-of select="."/>
											</Name2>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/Name3/EDXBABLE/ORIGINATOR/VALUE">
											<Name3>
												<xsl:value-of select="."/>
											</Name3>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/Street/EDXBABLE/ORIGINATOR/VALUE">
											<Street>
												<xsl:value-of select="."/>
											</Street>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/POBox/EDXBABLE/ORIGINATOR/VALUE">
											<POBox>
												<xsl:value-of select="."/>
											</POBox>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/HouseNumber/EDXBABLE/ORIGINATOR/VALUE">
											<HouseNumber>
												<xsl:value-of select="."/>
											</HouseNumber>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/StreetSupplement1/EDXBABLE/ORIGINATOR/VALUE">
											<StreetSupplement1>
												<xsl:value-of select="."/>
											</StreetSupplement1>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/StreetSupplement2/EDXBABLE/ORIGINATOR/VALUE">
											<StreetSupplement2>
												<xsl:value-of select="."/>
											</StreetSupplement2>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/Floor/EDXBABLE/ORIGINATOR/VALUE">
											<Floor>
												<xsl:value-of select="."/>
											</Floor>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/PostalCode/EDXBABLE/ORIGINATOR/VALUE">
											<PostalCode>
												<xsl:value-of select="."/>
											</PostalCode>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/City/EDXBABLE/ORIGINATOR/VALUE">
											<City>
												<xsl:value-of select="."/>
											</City>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/County/EDXBABLE/ORIGINATOR/VALUE">
											<County>
												<xsl:value-of select="."/>
											</County>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/Region">
											<Region>
												<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
													<RegionCoded>
														<xsl:value-of select="."/>
													</RegionCoded>
												</xsl:for-each>
												<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
													<RegionCodedOther>
														<xsl:value-of select="."/>
													</RegionCodedOther>
												</xsl:for-each>
											</Region>
										</xsl:for-each>
										<xsl:for-each select="NameAddress/District/EDXBABLE/ORIGINATOR/VALUE">
											<District>
												<xsl:value-of select="."/>
											</District>
										</xsl:for-each>
										<Country>
											<xsl:for-each select="NameAddress/Country/CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
												<CountryCoded>
													<xsl:value-of select="."/>
												</CountryCoded>
											</xsl:for-each>
										</Country>
									</NameAddress>
									<xsl:for-each select="OrderContact">
										<OrderContact>
											<xsl:for-each select="Contact">
												<Contact>
													<xsl:for-each select="ContactID">
														<ContactID>
															<xsl:for-each select="Identifier">
																<Identifier>
																	<xsl:for-each select="Agency">
																		<Agency>
																			<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<AgencyCoded>
																					<xsl:value-of select="."/>
																				</AgencyCoded>
																			</xsl:for-each>
																			<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<AgencyCodedOther>
																					<xsl:value-of select="."/>
																				</AgencyCodedOther>
																			</xsl:for-each>
																		</Agency>
																	</xsl:for-each>
																	<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																		<Ident>
																			<xsl:value-of select="."/>
																		</Ident>
																	</xsl:for-each>
																</Identifier>
															</xsl:for-each>
														</ContactID>
													</xsl:for-each>
													<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
														<ContactName>
															<xsl:value-of select="."/>
														</ContactName>
													</xsl:for-each>
													<xsl:for-each select="ListOfContactNumber">
														<ListOfContactNumber>
															<xsl:for-each select="ContactNumber">
																<ContactNumber>
																	<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																		<ContactNumberValue>
																			<xsl:value-of select="."/>
																		</ContactNumberValue>
																	</xsl:for-each>
																	<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																		<ContactNumberTypeCoded>
																			<xsl:value-of select="."/>
																		</ContactNumberTypeCoded>
																	</xsl:for-each>
																</ContactNumber>
															</xsl:for-each>
														</ListOfContactNumber>
													</xsl:for-each>
												</Contact>
											</xsl:for-each>
										</OrderContact>
									</xsl:for-each>
								</Party>
							</xsl:for-each>
						</SellerParty>
					</xsl:for-each>
					<xsl:for-each select="OrderParty/BuyerParty">
						<BuyerParty>
							<xsl:for-each select="Party">
								<Party>
									<xsl:for-each select="PartyID">
										<PartyID>
											<xsl:for-each select="Identifier">
												<Identifier>
													<xsl:for-each select="Agency">
														<Agency>
															<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																<AgencyCoded>
																	<xsl:value-of select="."/>
																</AgencyCoded>
															</xsl:for-each>
															<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																<AgencyCodedOther>
																	<xsl:value-of select="."/>
																</AgencyCodedOther>
															</xsl:for-each>
														</Agency>
													</xsl:for-each>
													<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
														<Ident>
															<xsl:value-of select="."/>
														</Ident>
													</xsl:for-each>
												</Identifier>
											</xsl:for-each>
										</PartyID>
									</xsl:for-each>
									<xsl:for-each select="ListOfIdentifier">
										<ListOfIdentifier>
											<xsl:for-each select="Identifier">
												<Identifier>
													<Agency>
														<xsl:for-each select="Agency/AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
															<AgencyCoded>
																<xsl:value-of select="."/>
															</AgencyCoded>
														</xsl:for-each>
														<xsl:for-each select="Agency/AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
															<AgencyCodedOther>
																<xsl:value-of select="."/>
															</AgencyCodedOther>
														</xsl:for-each>
													</Agency>
													<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
														<Ident>
															<xsl:value-of select="."/>
														</Ident>
													</xsl:for-each>
												</Identifier>
											</xsl:for-each>
										</ListOfIdentifier>
									</xsl:for-each>
									<xsl:for-each select="NameAddress">
										<NameAddress>
											<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
												<Name1>
													<xsl:value-of select="."/>
												</Name1>
											</xsl:for-each>
											<xsl:for-each select="Name2/EDXBABLE/ORIGINATOR/VALUE">
												<Name2>
													<xsl:value-of select="."/>
												</Name2>
											</xsl:for-each>
											<xsl:for-each select="Name3/EDXBABLE/ORIGINATOR/VALUE">
												<Name3>
													<xsl:value-of select="."/>
												</Name3>
											</xsl:for-each>
											<xsl:for-each select="Street/EDXBABLE/ORIGINATOR/VALUE">
												<Street>
													<xsl:value-of select="."/>
												</Street>
											</xsl:for-each>
											<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
												<POBox>
													<xsl:value-of select="."/>
												</POBox>
											</xsl:for-each>
											<xsl:for-each select="HouseNumber/EDXBABLE/ORIGINATOR/VALUE">
												<HouseNumber>
													<xsl:value-of select="."/>
												</HouseNumber>
											</xsl:for-each>
											<xsl:for-each select="StreetSupplement1/EDXBABLE/ORIGINATOR/VALUE">
												<StreetSupplement1>
													<xsl:value-of select="."/>
												</StreetSupplement1>
											</xsl:for-each>
											<xsl:for-each select="StreetSupplement2/EDXBABLE/ORIGINATOR/VALUE">
												<StreetSupplement2>
													<xsl:value-of select="."/>
												</StreetSupplement2>
											</xsl:for-each>
											<xsl:for-each select="Floor/EDXBABLE/ORIGINATOR/VALUE">
												<Floor>
													<xsl:value-of select="."/>
												</Floor>
											</xsl:for-each>
											<xsl:for-each select="PostalCode/EDXBABLE/ORIGINATOR/VALUE">
												<PostalCode>
													<xsl:value-of select="."/>
												</PostalCode>
											</xsl:for-each>
											<xsl:for-each select="City/EDXBABLE/ORIGINATOR/VALUE">
												<City>
													<xsl:value-of select="."/>
												</City>
											</xsl:for-each>
											<xsl:for-each select="Region">
												<Region>
													<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
														<RegionCoded>
															<xsl:value-of select="."/>
														</RegionCoded>
													</xsl:for-each>
													<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
														<RegionCodedOther>
															<xsl:value-of select="."/>
														</RegionCodedOther>
													</xsl:for-each>
												</Region>
											</xsl:for-each>
											<xsl:for-each select="County/EDXBABLE/ORIGINATOR/VALUE">
												<County>
													<xsl:value-of select="."/>
												</County>
												<District>
													<xsl:value-of select="."/>
												</District>
											</xsl:for-each>
											<xsl:for-each select="Country">
												<Country>
													<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
														<CountryCoded>
															<xsl:value-of select="."/>
														</CountryCoded>
													</xsl:for-each>
												</Country>
											</xsl:for-each>
											<xsl:for-each select="Timezone">
												<Timezone>
													<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
														<TimezoneCoded>
															<xsl:value-of select="."/>
														</TimezoneCoded>
													</xsl:for-each>
													<xsl:for-each select="TimezoneCodedOther/EDXBABLE/ORIGINATOR/VALUE">
														<TimezoneCodedOther>
															<xsl:value-of select="."/>
														</TimezoneCodedOther>
													</xsl:for-each>
												</Timezone>
											</xsl:for-each>
										</NameAddress>
									</xsl:for-each>
									<xsl:for-each select="OrderContact">
										<OrderContact>
											<xsl:for-each select="Contact">
												<Contact>
													<xsl:for-each select="ContactID">
														<ContactID>
															<xsl:for-each select="Identifier">
																<Identifier>
																	<xsl:for-each select="Agency">
																		<Agency>
																			<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<AgencyCoded>
																					<xsl:value-of select="."/>
																				</AgencyCoded>
																			</xsl:for-each>
																			<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<AgencyCodedOther>
																					<xsl:value-of select="."/>
																				</AgencyCodedOther>
																			</xsl:for-each>
																		</Agency>
																	</xsl:for-each>
																	<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																		<Ident>
																			<xsl:value-of select="."/>
																		</Ident>
																	</xsl:for-each>
																</Identifier>
															</xsl:for-each>
														</ContactID>
													</xsl:for-each>
													<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
														<ContactName>
															<xsl:value-of select="."/>
														</ContactName>
													</xsl:for-each>
													<xsl:for-each select="ListOfContactNumber">
														<ListOfContactNumber>
															<xsl:for-each select="ContactNumber">
																<ContactNumber>
																	<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																		<ContactNumberValue>
																			<xsl:value-of select="."/>
																		</ContactNumberValue>
																	</xsl:for-each>
																	<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																		<ContactNumberTypeCoded>
																			<xsl:value-of select="."/>
																		</ContactNumberTypeCoded>
																	</xsl:for-each>
																</ContactNumber>
															</xsl:for-each>
														</ListOfContactNumber>
													</xsl:for-each>
												</Contact>
											</xsl:for-each>
										</OrderContact>
									</xsl:for-each>
									<xsl:for-each select="OtherContacts">
										<OtherContacts>
											<xsl:for-each select="ListOfContact">
												<ListOfContact>
													<xsl:for-each select="Contact">
														<Contact>
															<ContactID>
																<Identifier>
																	<Agency>
																		<xsl:for-each select="ContactID/Identifier/Agency/AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<AgencyCoded>
																				<xsl:value-of select="."/>
																			</AgencyCoded>
																		</xsl:for-each>
																		<xsl:for-each select="ContactID/Identifier/Agency/AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																			<AgencyCodedOther>
																				<xsl:value-of select="."/>
																			</AgencyCodedOther>
																		</xsl:for-each>
																	</Agency>
																	<xsl:for-each select="ContactID/Identifier/Ident/EDXBABLE/ORIGINATOR/VALUE">
																		<Ident>
																			<xsl:value-of select="."/>
																		</Ident>
																	</xsl:for-each>
																</Identifier>
															</ContactID>
															<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																<ContactName>
																	<xsl:value-of select="."/>
																</ContactName>
															</xsl:for-each>
															<xsl:for-each select="ContactFunction">
																<ContactFunction>
																	<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																		<ContactFunctionCoded>
																			<xsl:value-of select="."/>
																		</ContactFunctionCoded>
																	</xsl:for-each>
																	<xsl:for-each select="ContactFunctionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																		<ContactFunctionCodedOther>
																			<xsl:value-of select="."/>
																		</ContactFunctionCodedOther>
																	</xsl:for-each>
																</ContactFunction>
															</xsl:for-each>
															<xsl:for-each select="ListOfContactNumber">
																<ListOfContactNumber>
																	<xsl:for-each select="ContactNumber">
																		<ContactNumber>
																			<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																				<ContactNumberValue>
																					<xsl:value-of select="."/>
																				</ContactNumberValue>
																			</xsl:for-each>
																			<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<ContactNumberTypeCoded>
																					<xsl:value-of select="."/>
																				</ContactNumberTypeCoded>
																			</xsl:for-each>
																		</ContactNumber>
																	</xsl:for-each>
																</ListOfContactNumber>
															</xsl:for-each>
														</Contact>
													</xsl:for-each>
												</ListOfContact>
											</xsl:for-each>
										</OtherContacts>
									</xsl:for-each>
								</Party>
							</xsl:for-each>
						</BuyerParty>
					</xsl:for-each>
					<Purpose>
						<PurposeCoded>Original</PurposeCoded>
					</Purpose>
					<ResponseType>
						<xsl:for-each select="TempItemCoded/EDXBABLE/ORIGINATOR/VALUE">
							<ResponseTypeCoded>
								<xsl:value-of select="."/>
							</ResponseTypeCoded>
						</xsl:for-each>
					</ResponseType>
					<OriginalOrderHeader>
						<xsl:for-each select=".">
							<OrderHeader>
								<xsl:for-each select="OrderNumber">
									<OrderNumber>
										<xsl:for-each select="BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
											<BuyerOrderNumber>
												<xsl:value-of select="."/>
											</BuyerOrderNumber>
										</xsl:for-each>
									</OrderNumber>
								</xsl:for-each>
								<xsl:for-each select="OrderIssueDate/EDXBABLE/ORIGINATOR/VALUE">
									<OrderIssueDate>
										<xsl:value-of select="."/>
									</OrderIssueDate>
								</xsl:for-each>
								<xsl:for-each select="OrderReferences">
									<OrderReferences>
										<AccountCode>
											<Reference>
												<xsl:for-each select="AccountCode/Reference/RefNum/EDXBABLE/ORIGINATOR/VALUE">
													<RefNum>
														<xsl:value-of select="."/>
													</RefNum>
												</xsl:for-each>
											</Reference>
										</AccountCode>
										<xsl:for-each select="OtherOrderReferences">
											<OtherOrderReferences>
												<xsl:for-each select="ListOfReferenceCoded">
													<ListOfReferenceCoded>
														<xsl:for-each select="ReferenceCoded">
															<ReferenceCoded>
																<xsl:for-each select="ReferenceTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																	<ReferenceTypeCoded>
																		<xsl:value-of select="."/>
																	</ReferenceTypeCoded>
																</xsl:for-each>
																<xsl:for-each select="ReferenceTypeCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																	<ReferenceTypeCodedOther>
																		<xsl:value-of select="."/>
																	</ReferenceTypeCodedOther>
																</xsl:for-each>
																<PrimaryReference>
																	<Reference>
																		<xsl:for-each select="PrimaryReference/Reference/RefNum/EDXBABLE/ORIGINATOR/VALUE">
																			<RefNum>
																				<xsl:value-of select="."/>
																			</RefNum>
																		</xsl:for-each>
																	</Reference>
																</PrimaryReference>
															</ReferenceCoded>
														</xsl:for-each>
													</ListOfReferenceCoded>
												</xsl:for-each>
											</OtherOrderReferences>
										</xsl:for-each>
									</OrderReferences>
								</xsl:for-each>
								<xsl:for-each select="Purpose">
									<Purpose>
										<xsl:for-each select="PurposeCoded/EDXBABLE/ORIGINATOR/VALUE">
											<PurposeCoded>
												<xsl:value-of select="."/>
											</PurposeCoded>
										</xsl:for-each>
									</Purpose>
								</xsl:for-each>
								<xsl:for-each select="RequestedResponse">
									<RequestedResponse>
										<xsl:for-each select="RequestedResponseCoded/EDXBABLE/ORIGINATOR/VALUE">
											<RequestedResponseCoded>
												<xsl:value-of select="."/>
											</RequestedResponseCoded>
										</xsl:for-each>
									</RequestedResponse>
								</xsl:for-each>
								<xsl:for-each select="OrderType">
									<OrderType>
										<xsl:for-each select="OrderTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
											<OrderTypeCoded>
												<xsl:value-of select="."/>
											</OrderTypeCoded>
										</xsl:for-each>
									</OrderType>
								</xsl:for-each>
								<xsl:for-each select="OrderCurrency">
									<OrderCurrency>
										<xsl:for-each select="Currency">
											<Currency>
												<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
													<CurrencyCoded>
														<xsl:value-of select="."/>
													</CurrencyCoded>
												</xsl:for-each>
											</Currency>
										</xsl:for-each>
									</OrderCurrency>
								</xsl:for-each>
								<xsl:for-each select="OrderLanguage">
									<OrderLanguage>
										<xsl:for-each select="Language">
											<Language>
												<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
													<LanguageCoded>
														<xsl:value-of select="."/>
													</LanguageCoded>
												</xsl:for-each>
											</Language>
										</xsl:for-each>
									</OrderLanguage>
								</xsl:for-each>
								<xsl:for-each select="OrderDates">
									<OrderDates>
										<xsl:for-each select="ListOfDateCoded">
											<ListOfDateCoded>
												<DateCoded>
													<xsl:for-each select="DateCoded/Date/EDXBABLE/ORIGINATOR/VALUE">
														<Date>
															<xsl:value-of select="."/>
														</Date>
													</xsl:for-each>
													<DateQualifier>
														<xsl:for-each select="DateCoded/DateQualifier/DateQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
															<DateQualifierCoded>
																<xsl:value-of select="."/>
															</DateQualifierCoded>
														</xsl:for-each>
													</DateQualifier>
												</DateCoded>
											</ListOfDateCoded>
										</xsl:for-each>
									</OrderDates>
								</xsl:for-each>
								<xsl:for-each select="OrderParty">
									<OrderParty>
										<xsl:for-each select="BuyerParty">
											<BuyerParty>
												<xsl:for-each select="Party">
													<Party>
														<xsl:for-each select="PartyID">
															<PartyID>
																<xsl:for-each select="Identifier">
																	<Identifier>
																		<xsl:for-each select="Agency">
																			<Agency>
																				<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCoded>
																						<xsl:value-of select="."/>
																					</AgencyCoded>
																				</xsl:for-each>
																				<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCodedOther>
																						<xsl:value-of select="."/>
																					</AgencyCodedOther>
																				</xsl:for-each>
																			</Agency>
																		</xsl:for-each>
																		<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</xsl:for-each>
															</PartyID>
														</xsl:for-each>
														<xsl:for-each select="ListOfIdentifier">
															<ListOfIdentifier>
																<xsl:for-each select="Identifier">
																	<Identifier>
																		<xsl:for-each select="Agency">
																			<Agency>
																				<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCoded>
																						<xsl:value-of select="."/>
																					</AgencyCoded>
																				</xsl:for-each>
																				<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCodedOther>
																						<xsl:value-of select="."/>
																					</AgencyCodedOther>
																				</xsl:for-each>
																			</Agency>
																		</xsl:for-each>
																		<xsl:for-each select="../../PartyID/Identifier/Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</xsl:for-each>
															</ListOfIdentifier>
														</xsl:for-each>
														<xsl:for-each select="NameAddress">
															<NameAddress>
																<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																	<Name1>
																		<xsl:value-of select="."/>
																	</Name1>
																</xsl:for-each>
																<xsl:for-each select="Name2/EDXBABLE/ORIGINATOR/VALUE">
																	<Name2>
																		<xsl:value-of select="."/>
																	</Name2>
																</xsl:for-each>
																<xsl:for-each select="Name3/EDXBABLE/ORIGINATOR/VALUE">
																	<Name3>
																		<xsl:value-of select="."/>
																	</Name3>
																</xsl:for-each>
																<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																	<POBox>
																		<xsl:value-of select="."/>
																	</POBox>
																</xsl:for-each>
																<xsl:for-each select="Street/EDXBABLE/ORIGINATOR/VALUE">
																	<Street>
																		<xsl:value-of select="."/>
																	</Street>
																</xsl:for-each>
																<xsl:for-each select="StreetSupplement1/EDXBABLE/ORIGINATOR/VALUE">
																	<StreetSupplement1>
																		<xsl:value-of select="."/>
																	</StreetSupplement1>
																</xsl:for-each>
																<xsl:for-each select="StreetSupplement2/EDXBABLE/ORIGINATOR/VALUE">
																	<StreetSupplement2>
																		<xsl:value-of select="."/>
																	</StreetSupplement2>
																</xsl:for-each>
																<xsl:for-each select="Building/EDXBABLE/ORIGINATOR/VALUE">
																	<Building>
																		<xsl:value-of select="."/>
																	</Building>
																</xsl:for-each>
																<xsl:for-each select="PostalCode/EDXBABLE/ORIGINATOR/VALUE">
																	<PostalCode>
																		<xsl:value-of select="."/>
																	</PostalCode>
																</xsl:for-each>
																<xsl:for-each select="City/EDXBABLE/ORIGINATOR/VALUE">
																	<City>
																		<xsl:value-of select="."/>
																	</City>
																</xsl:for-each>
																<xsl:for-each select="Region">
																	<Region>
																		<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<RegionCoded>
																				<xsl:value-of select="."/>
																			</RegionCoded>
																		</xsl:for-each>
																		<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																			<RegionCodedOther>
																				<xsl:value-of select="."/>
																			</RegionCodedOther>
																		</xsl:for-each>
																	</Region>
																</xsl:for-each>
																<xsl:for-each select="Country">
																	<Country>
																		<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<CountryCoded>
																				<xsl:value-of select="."/>
																			</CountryCoded>
																		</xsl:for-each>
																	</Country>
																</xsl:for-each>
																<xsl:for-each select="Timezone">
																	<Timezone>
																		<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<TimezoneCoded>
																				<xsl:value-of select="."/>
																			</TimezoneCoded>
																		</xsl:for-each>
																	</Timezone>
																</xsl:for-each>
															</NameAddress>
														</xsl:for-each>
														<xsl:for-each select="OrderContact">
															<OrderContact>
																<xsl:for-each select="Contact">
																	<Contact>
																		<xsl:for-each select="ContactID">
																			<ContactID>
																				<xsl:for-each select="Identifier">
																					<Identifier>
																						<xsl:for-each select="Agency">
																							<Agency>
																								<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																									<AgencyCoded>
																										<xsl:value-of select="."/>
																									</AgencyCoded>
																								</xsl:for-each>
																								<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																									<AgencyCodedOther>
																										<xsl:value-of select="."/>
																									</AgencyCodedOther>
																								</xsl:for-each>
																							</Agency>
																						</xsl:for-each>
																						<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																							<Ident>
																								<xsl:value-of select="."/>
																							</Ident>
																						</xsl:for-each>
																					</Identifier>
																				</xsl:for-each>
																			</ContactID>
																		</xsl:for-each>
																		<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																			<ContactName>
																				<xsl:value-of select="."/>
																			</ContactName>
																		</xsl:for-each>
																		<xsl:for-each select="ContactFunction">
																			<ContactFunction>
																				<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<ContactFunctionCoded>
																						<xsl:value-of select="."/>
																					</ContactFunctionCoded>
																				</xsl:for-each>
																				<xsl:for-each select="ContactFunctionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																					<ContactFunctionCodedOther>
																						<xsl:value-of select="."/>
																					</ContactFunctionCodedOther>
																				</xsl:for-each>
																			</ContactFunction>
																		</xsl:for-each>
																		<xsl:for-each select="ListOfContactNumber">
																			<ListOfContactNumber>
																				<xsl:for-each select="ContactNumber">
																					<ContactNumber>
																						<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactNumberValue>
																								<xsl:value-of select="."/>
																							</ContactNumberValue>
																						</xsl:for-each>
																						<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactNumberTypeCoded>
																								<xsl:value-of select="."/>
																							</ContactNumberTypeCoded>
																						</xsl:for-each>
																					</ContactNumber>
																				</xsl:for-each>
																			</ListOfContactNumber>
																		</xsl:for-each>
																	</Contact>
																</xsl:for-each>
															</OrderContact>
														</xsl:for-each>
														<OtherContacts>
															<xsl:for-each select="OtherContacts/ListOfContact">
																<ListOfContact>
																	<xsl:for-each select="Contact">
																		<Contact>
																			<xsl:for-each select="ContactID">
																				<ContactID>
																					<xsl:for-each select="Identifier">
																						<Identifier>
																							<xsl:for-each select="Agency">
																								<Agency>
																									<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCoded>
																											<xsl:value-of select="."/>
																										</AgencyCoded>
																									</xsl:for-each>
																									<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCodedOther>
																											<xsl:value-of select="."/>
																										</AgencyCodedOther>
																									</xsl:for-each>
																								</Agency>
																							</xsl:for-each>
																							<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</xsl:for-each>
																				</ContactID>
																			</xsl:for-each>
																			<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																				<ContactName>
																					<xsl:value-of select="."/>
																				</ContactName>
																			</xsl:for-each>
																			<xsl:for-each select="ContactFunction">
																				<ContactFunction>
																					<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactFunctionCoded>
																							<xsl:value-of select="."/>
																						</ContactFunctionCoded>
																					</xsl:for-each>
																					<xsl:for-each select="ContactFunctionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactFunctionCodedOther>
																							<xsl:value-of select="."/>
																						</ContactFunctionCodedOther>
																					</xsl:for-each>
																				</ContactFunction>
																			</xsl:for-each>
																			<xsl:for-each select="ContactDescription/EDXBABLE/ORIGINATOR/VALUE">
																				<ContactDescription>
																					<xsl:value-of select="."/>
																				</ContactDescription>
																			</xsl:for-each>
																			<xsl:for-each select="ListOfContactNumber">
																				<ListOfContactNumber>
																					<ContactNumber>
																						<xsl:for-each select="ContactNumber/ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactNumberValue>
																								<xsl:value-of select="."/>
																							</ContactNumberValue>
																						</xsl:for-each>
																						<xsl:for-each select="ContactNumber/ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactNumberTypeCoded>
																								<xsl:value-of select="."/>
																							</ContactNumberTypeCoded>
																						</xsl:for-each>
																					</ContactNumber>
																				</ListOfContactNumber>
																			</xsl:for-each>
																		</Contact>
																	</xsl:for-each>
																</ListOfContact>
															</xsl:for-each>
														</OtherContacts>
														<xsl:for-each select="CorrespondenceLanguage">
															<CorrespondenceLanguage>
																<xsl:for-each select="Language">
																	<Language>
																		<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<LanguageCoded>
																				<xsl:value-of select="."/>
																			</LanguageCoded>
																		</xsl:for-each>
																	</Language>
																</xsl:for-each>
															</CorrespondenceLanguage>
														</xsl:for-each>
													</Party>
												</xsl:for-each>
											</BuyerParty>
										</xsl:for-each>
										<xsl:for-each select="BuyerTaxInformation">
											<BuyerTaxInformation>
												<xsl:for-each select="PartyTaxInformation">
													<PartyTaxInformation>
														<xsl:for-each select="RegisteredName/EDXBABLE/ORIGINATOR/VALUE">
															<RegisteredName>
																<xsl:value-of select="."/>
															</RegisteredName>
														</xsl:for-each>
														<xsl:for-each select="CompanyRegistrationNumber/EDXBABLE/ORIGINATOR/VALUE">
															<CompanyRegistrationNumber>
																<xsl:value-of select="."/>
															</CompanyRegistrationNumber>
														</xsl:for-each>
													</PartyTaxInformation>
												</xsl:for-each>
											</BuyerTaxInformation>
										</xsl:for-each>
										<xsl:for-each select="SellerParty">
											<SellerParty>
												<xsl:for-each select="Party">
													<Party>
														<xsl:for-each select="PartyID">
															<PartyID>
																<xsl:for-each select="Identifier">
																	<Identifier>
																		<xsl:for-each select="Agency">
																			<Agency>
																				<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCoded>
																						<xsl:value-of select="."/>
																					</AgencyCoded>
																				</xsl:for-each>
																				<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCodedOther>
																						<xsl:value-of select="."/>
																					</AgencyCodedOther>
																				</xsl:for-each>
																			</Agency>
																		</xsl:for-each>
																		<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</xsl:for-each>
															</PartyID>
														</xsl:for-each>
														<xsl:for-each select="ListOfIdentifier">
															<ListOfIdentifier>
																<xsl:for-each select="Identifier">
																	<Identifier>
																		<xsl:for-each select="Agency">
																			<Agency>
																				<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCoded>
																						<xsl:value-of select="."/>
																					</AgencyCoded>
																				</xsl:for-each>
																				<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCodedOther>
																						<xsl:value-of select="."/>
																					</AgencyCodedOther>
																				</xsl:for-each>
																			</Agency>
																		</xsl:for-each>
																		<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</xsl:for-each>
															</ListOfIdentifier>
														</xsl:for-each>
														<xsl:for-each select="OtherContacts">
															<OtherContacts>
																<xsl:for-each select="ListOfContact">
																	<ListOfContact>
																		<xsl:for-each select="Contact">
																			<Contact>
																				<xsl:for-each select="ContactID">
																					<ContactID>
																						<xsl:for-each select="Identifier">
																							<Identifier>
																								<xsl:for-each select="Agency">
																									<Agency>
																										<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																											<AgencyCoded>
																												<xsl:value-of select="."/>
																											</AgencyCoded>
																										</xsl:for-each>
																										<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																											<AgencyCodedOther>
																												<xsl:value-of select="."/>
																											</AgencyCodedOther>
																										</xsl:for-each>
																									</Agency>
																								</xsl:for-each>
																								<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																									<Ident>
																										<xsl:value-of select="."/>
																									</Ident>
																								</xsl:for-each>
																							</Identifier>
																						</xsl:for-each>
																					</ContactID>
																				</xsl:for-each>
																				<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																					<ContactName>
																						<xsl:value-of select="."/>
																					</ContactName>
																				</xsl:for-each>
																				<xsl:for-each select="ContactFunction">
																					<ContactFunction>
																						<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactFunctionCoded>
																								<xsl:value-of select="."/>
																							</ContactFunctionCoded>
																						</xsl:for-each>
																					</ContactFunction>
																				</xsl:for-each>
																				<xsl:for-each select="ListOfContactNumber">
																					<ListOfContactNumber>
																						<xsl:for-each select="ContactNumber">
																							<ContactNumber>
																								<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																									<ContactNumberValue>
																										<xsl:value-of select="."/>
																									</ContactNumberValue>
																								</xsl:for-each>
																								<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																									<ContactNumberTypeCoded>
																										<xsl:value-of select="."/>
																									</ContactNumberTypeCoded>
																								</xsl:for-each>
																							</ContactNumber>
																						</xsl:for-each>
																					</ListOfContactNumber>
																				</xsl:for-each>
																			</Contact>
																		</xsl:for-each>
																	</ListOfContact>
																</xsl:for-each>
															</OtherContacts>
														</xsl:for-each>
														<xsl:for-each select="CorrespondenceLanguage">
															<CorrespondenceLanguage>
																<xsl:for-each select="Language">
																	<Language>
																		<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<LanguageCoded>
																				<xsl:value-of select="."/>
																			</LanguageCoded>
																		</xsl:for-each>
																	</Language>
																</xsl:for-each>
															</CorrespondenceLanguage>
														</xsl:for-each>
													</Party>
												</xsl:for-each>
											</SellerParty>
										</xsl:for-each>
										<xsl:for-each select="ShipToParty">
											<ShipToParty>
												<xsl:for-each select="Party">
													<Party>
														<xsl:for-each select="PartyID">
															<PartyID>
																<xsl:for-each select="Identifier">
																	<Identifier>
																		<xsl:for-each select="Agency">
																			<Agency>
																				<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCoded>
																						<xsl:value-of select="."/>
																					</AgencyCoded>
																				</xsl:for-each>
																				<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCodedOther>
																						<xsl:value-of select="."/>
																					</AgencyCodedOther>
																				</xsl:for-each>
																			</Agency>
																		</xsl:for-each>
																		<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</xsl:for-each>
															</PartyID>
														</xsl:for-each>
														<xsl:for-each select="ListOfIdentifier">
															<ListOfIdentifier>
																<xsl:for-each select="Identifier">
																	<Identifier>
																		<xsl:for-each select="Agency">
																			<Agency>
																				<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCoded>
																						<xsl:value-of select="."/>
																					</AgencyCoded>
																				</xsl:for-each>
																				<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCodedOther>
																						<xsl:value-of select="."/>
																					</AgencyCodedOther>
																				</xsl:for-each>
																			</Agency>
																		</xsl:for-each>
																		<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</xsl:for-each>
															</ListOfIdentifier>
														</xsl:for-each>
														<xsl:for-each select="NameAddress">
															<NameAddress>
																<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																	<Name1>
																		<xsl:value-of select="."/>
																	</Name1>
																</xsl:for-each>
																<xsl:for-each select="Name2/EDXBABLE/ORIGINATOR/VALUE">
																	<Name2>
																		<xsl:value-of select="."/>
																	</Name2>
																</xsl:for-each>
																<xsl:for-each select="Name3/EDXBABLE/ORIGINATOR/VALUE">
																	<Name3>
																		<xsl:value-of select="."/>
																	</Name3>
																</xsl:for-each>
																<xsl:for-each select="Street/EDXBABLE/ORIGINATOR/VALUE">
																	<Street>
																		<xsl:value-of select="."/>
																	</Street>
																</xsl:for-each>
																<xsl:for-each select="HouseNumber/EDXBABLE/ORIGINATOR/VALUE">
																	<HouseNumber>
																		<xsl:value-of select="."/>
																	</HouseNumber>
																</xsl:for-each>
																<xsl:for-each select="StreetSupplement1/EDXBABLE/ORIGINATOR/VALUE">
																	<StreetSupplement1>
																		<xsl:value-of select="."/>
																	</StreetSupplement1>
																</xsl:for-each>
																<xsl:for-each select="StreetSupplement2/EDXBABLE/ORIGINATOR/VALUE">
																	<StreetSupplement2>
																		<xsl:value-of select="."/>
																	</StreetSupplement2>
																</xsl:for-each>
																<xsl:for-each select="Building/EDXBABLE/ORIGINATOR/VALUE">
																	<Building>
																		<xsl:value-of select="."/>
																	</Building>
																</xsl:for-each>
																<xsl:for-each select="Floor/EDXBABLE/ORIGINATOR/VALUE">
																	<Floor>
																		<xsl:value-of select="."/>
																	</Floor>
																</xsl:for-each>
																<xsl:for-each select="RoomNumber/EDXBABLE/ORIGINATOR/VALUE">
																	<RoomNumber>
																		<xsl:value-of select="."/>
																	</RoomNumber>
																</xsl:for-each>
																<xsl:for-each select="PostalCode/EDXBABLE/ORIGINATOR/VALUE">
																	<PostalCode>
																		<xsl:value-of select="."/>
																	</PostalCode>
																</xsl:for-each>
																<xsl:for-each select="City/EDXBABLE/ORIGINATOR/VALUE">
																	<City>
																		<xsl:value-of select="."/>
																	</City>
																</xsl:for-each>
																<xsl:for-each select="Region">
																	<Region>
																		<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<RegionCoded>
																				<xsl:value-of select="."/>
																			</RegionCoded>
																		</xsl:for-each>
																		<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																			<RegionCodedOther>
																				<xsl:value-of select="."/>
																			</RegionCodedOther>
																		</xsl:for-each>
																	</Region>
																</xsl:for-each>
																<xsl:for-each select="Country">
																	<Country>
																		<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<CountryCoded>
																				<xsl:value-of select="."/>
																			</CountryCoded>
																		</xsl:for-each>
																		<xsl:for-each select="CountryCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																			<CountryCodedOther>
																				<xsl:value-of select="."/>
																			</CountryCodedOther>
																		</xsl:for-each>
																	</Country>
																</xsl:for-each>
																<xsl:for-each select="Timezone">
																	<Timezone>
																		<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<TimezoneCoded>
																				<xsl:value-of select="."/>
																			</TimezoneCoded>
																		</xsl:for-each>
																	</Timezone>
																</xsl:for-each>
															</NameAddress>
														</xsl:for-each>
														<xsl:for-each select="OrderContact">
															<OrderContact>
																<xsl:for-each select="Contact">
																	<Contact>
																		<xsl:for-each select="ContactID">
																			<ContactID>
																				<xsl:for-each select="Identifier">
																					<Identifier>
																						<xsl:for-each select="Agency">
																							<Agency>
																								<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																									<AgencyCoded>
																										<xsl:value-of select="."/>
																									</AgencyCoded>
																								</xsl:for-each>
																								<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																									<AgencyCodedOther>
																										<xsl:value-of select="."/>
																									</AgencyCodedOther>
																								</xsl:for-each>
																							</Agency>
																						</xsl:for-each>
																						<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																							<Ident>
																								<xsl:value-of select="."/>
																							</Ident>
																						</xsl:for-each>
																					</Identifier>
																				</xsl:for-each>
																			</ContactID>
																		</xsl:for-each>
																		<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																			<ContactName>
																				<xsl:value-of select="."/>
																			</ContactName>
																		</xsl:for-each>
																		<xsl:for-each select="ContactFunction">
																			<ContactFunction>
																				<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<ContactFunctionCoded>
																						<xsl:value-of select="."/>
																					</ContactFunctionCoded>
																				</xsl:for-each>
																			</ContactFunction>
																		</xsl:for-each>
																		<xsl:for-each select="ListOfContactNumber">
																			<ListOfContactNumber>
																				<xsl:for-each select="ContactNumber">
																					<ContactNumber>
																						<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactNumberValue>
																								<xsl:value-of select="."/>
																							</ContactNumberValue>
																						</xsl:for-each>
																						<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactNumberTypeCoded>
																								<xsl:value-of select="."/>
																							</ContactNumberTypeCoded>
																						</xsl:for-each>
																					</ContactNumber>
																				</xsl:for-each>
																			</ListOfContactNumber>
																		</xsl:for-each>
																	</Contact>
																</xsl:for-each>
															</OrderContact>
														</xsl:for-each>
														<xsl:for-each select="ReceivingContact">
															<ReceivingContact>
																<Contact>
																	<ContactName/>
																</Contact>
															</ReceivingContact>
														</xsl:for-each>
														<xsl:for-each select="OtherContacts">
															<OtherContacts>
																<xsl:for-each select="ListOfContact">
																	<ListOfContact>
																		<xsl:for-each select="Contact">
																			<Contact>
																				<xsl:for-each select="ContactID">
																					<ContactID>
																						<xsl:for-each select="Identifier">
																							<Identifier>
																								<xsl:for-each select="Agency">
																									<Agency>
																										<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																											<AgencyCoded>
																												<xsl:value-of select="."/>
																											</AgencyCoded>
																										</xsl:for-each>
																										<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																											<AgencyCodedOther>
																												<xsl:value-of select="."/>
																											</AgencyCodedOther>
																										</xsl:for-each>
																									</Agency>
																								</xsl:for-each>
																								<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																									<Ident>
																										<xsl:value-of select="."/>
																									</Ident>
																								</xsl:for-each>
																							</Identifier>
																						</xsl:for-each>
																					</ContactID>
																				</xsl:for-each>
																				<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																					<ContactName>
																						<xsl:value-of select="."/>
																					</ContactName>
																				</xsl:for-each>
																				<xsl:for-each select="ContactFunction">
																					<ContactFunction>
																						<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactFunctionCoded>
																								<xsl:value-of select="."/>
																							</ContactFunctionCoded>
																						</xsl:for-each>
																					</ContactFunction>
																				</xsl:for-each>
																				<xsl:for-each select="ListOfContactNumber">
																					<ListOfContactNumber>
																						<xsl:for-each select="ContactNumber">
																							<ContactNumber>
																								<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																									<ContactNumberValue>
																										<xsl:value-of select="."/>
																									</ContactNumberValue>
																								</xsl:for-each>
																								<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																									<ContactNumberTypeCoded>
																										<xsl:value-of select="."/>
																									</ContactNumberTypeCoded>
																								</xsl:for-each>
																							</ContactNumber>
																						</xsl:for-each>
																					</ListOfContactNumber>
																				</xsl:for-each>
																			</Contact>
																		</xsl:for-each>
																	</ListOfContact>
																</xsl:for-each>
															</OtherContacts>
														</xsl:for-each>
														<xsl:for-each select="CorrespondenceLanguage">
															<CorrespondenceLanguage>
																<xsl:for-each select="Language">
																	<Language>
																		<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<LanguageCoded>
																				<xsl:value-of select="."/>
																			</LanguageCoded>
																		</xsl:for-each>
																	</Language>
																</xsl:for-each>
															</CorrespondenceLanguage>
														</xsl:for-each>
													</Party>
												</xsl:for-each>
											</ShipToParty>
										</xsl:for-each>
										<xsl:for-each select="BillToParty">
											<BillToParty>
												<xsl:for-each select="Party">
													<Party>
														<xsl:for-each select="PartyID">
															<PartyID>
																<xsl:for-each select="Identifier">
																	<Identifier>
																		<xsl:for-each select="Agency">
																			<Agency>
																				<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCoded>
																						<xsl:value-of select="."/>
																					</AgencyCoded>
																				</xsl:for-each>
																				<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCodedOther>
																						<xsl:value-of select="."/>
																					</AgencyCodedOther>
																				</xsl:for-each>
																			</Agency>
																		</xsl:for-each>
																		<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</xsl:for-each>
															</PartyID>
														</xsl:for-each>
														<xsl:for-each select="ListOfIdentifier">
															<ListOfIdentifier>
																<xsl:for-each select="Identifier">
																	<Identifier>
																		<Agency>
																			<xsl:for-each select="Agency/AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<AgencyCoded>
																					<xsl:value-of select="."/>
																				</AgencyCoded>
																			</xsl:for-each>
																			<xsl:for-each select="Agency/AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<AgencyCodedOther>
																					<xsl:value-of select="."/>
																				</AgencyCodedOther>
																			</xsl:for-each>
																		</Agency>
																		<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</xsl:for-each>
															</ListOfIdentifier>
														</xsl:for-each>
														<xsl:for-each select="NameAddress">
															<NameAddress>
																<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																	<Name1>
																		<xsl:value-of select="."/>
																	</Name1>
																</xsl:for-each>
																<xsl:for-each select="Name2/EDXBABLE/ORIGINATOR/VALUE">
																	<Name2>
																		<xsl:value-of select="."/>
																	</Name2>
																</xsl:for-each>
																<xsl:for-each select="Name3/EDXBABLE/ORIGINATOR/VALUE">
																	<Name3>
																		<xsl:value-of select="."/>
																	</Name3>
																</xsl:for-each>
																<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																	<POBox>
																		<xsl:value-of select="."/>
																	</POBox>
																</xsl:for-each>
																<xsl:for-each select="Street/EDXBABLE/ORIGINATOR/VALUE">
																	<Street>
																		<xsl:value-of select="."/>
																	</Street>
																</xsl:for-each>
																<xsl:for-each select="HouseNumber/EDXBABLE/ORIGINATOR/VALUE">
																	<HouseNumber>
																		<xsl:value-of select="."/>
																	</HouseNumber>
																</xsl:for-each>
																<xsl:for-each select="StreetSupplement1/EDXBABLE/ORIGINATOR/VALUE">
																	<StreetSupplement1>
																		<xsl:value-of select="."/>
																	</StreetSupplement1>
																</xsl:for-each>
																<xsl:for-each select="StreetSupplement2/EDXBABLE/ORIGINATOR/VALUE">
																	<StreetSupplement2>
																		<xsl:value-of select="."/>
																	</StreetSupplement2>
																</xsl:for-each>
																<xsl:for-each select="Building/EDXBABLE/ORIGINATOR/VALUE">
																	<Building>
																		<xsl:value-of select="."/>
																	</Building>
																</xsl:for-each>
																<xsl:for-each select="Floor/EDXBABLE/ORIGINATOR/VALUE">
																	<Floor>
																		<xsl:value-of select="."/>
																	</Floor>
																</xsl:for-each>
																<xsl:for-each select="PostalCode/EDXBABLE/ORIGINATOR/VALUE">
																	<PostalCode>
																		<xsl:value-of select="."/>
																	</PostalCode>
																</xsl:for-each>
																<xsl:for-each select="City/EDXBABLE/ORIGINATOR/VALUE">
																	<City>
																		<xsl:value-of select="."/>
																	</City>
																</xsl:for-each>
																<xsl:for-each select="Region">
																	<Region>
																		<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<RegionCoded>
																				<xsl:value-of select="."/>
																			</RegionCoded>
																		</xsl:for-each>
																		<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																			<RegionCodedOther>
																				<xsl:value-of select="."/>
																			</RegionCodedOther>
																		</xsl:for-each>
																	</Region>
																</xsl:for-each>
																<xsl:for-each select="Country">
																	<Country>
																		<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<CountryCoded>
																				<xsl:value-of select="."/>
																			</CountryCoded>
																		</xsl:for-each>
																	</Country>
																</xsl:for-each>
																<xsl:for-each select="Timezone">
																	<Timezone>
																		<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<TimezoneCoded>
																				<xsl:value-of select="."/>
																			</TimezoneCoded>
																		</xsl:for-each>
																	</Timezone>
																</xsl:for-each>
															</NameAddress>
														</xsl:for-each>
														<xsl:for-each select="OrderContact">
															<OrderContact>
																<xsl:for-each select="Contact">
																	<Contact>
																		<xsl:for-each select="ContactID">
																			<ContactID>
																				<xsl:for-each select="Identifier">
																					<Identifier>
																						<xsl:for-each select="Agency">
																							<Agency>
																								<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																									<AgencyCoded>
																										<xsl:value-of select="."/>
																									</AgencyCoded>
																								</xsl:for-each>
																								<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																									<AgencyCodedOther>
																										<xsl:value-of select="."/>
																									</AgencyCodedOther>
																								</xsl:for-each>
																							</Agency>
																						</xsl:for-each>
																						<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																							<Ident>
																								<xsl:value-of select="."/>
																							</Ident>
																						</xsl:for-each>
																					</Identifier>
																				</xsl:for-each>
																			</ContactID>
																		</xsl:for-each>
																		<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																			<ContactName>
																				<xsl:value-of select="."/>
																			</ContactName>
																		</xsl:for-each>
																		<xsl:for-each select="ContactFunction">
																			<ContactFunction>
																				<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<ContactFunctionCoded>
																						<xsl:value-of select="."/>
																					</ContactFunctionCoded>
																				</xsl:for-each>
																			</ContactFunction>
																		</xsl:for-each>
																		<xsl:for-each select="ListOfContactNumber">
																			<ListOfContactNumber>
																				<xsl:for-each select="ContactNumber">
																					<ContactNumber>
																						<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactNumberValue>
																								<xsl:value-of select="."/>
																							</ContactNumberValue>
																						</xsl:for-each>
																						<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactNumberTypeCoded>
																								<xsl:value-of select="."/>
																							</ContactNumberTypeCoded>
																						</xsl:for-each>
																					</ContactNumber>
																				</xsl:for-each>
																			</ListOfContactNumber>
																		</xsl:for-each>
																	</Contact>
																</xsl:for-each>
															</OrderContact>
														</xsl:for-each>
														<xsl:for-each select="OtherContacts">
															<OtherContacts>
																<xsl:for-each select="ListOfContact">
																	<ListOfContact>
																		<xsl:for-each select="Contact">
																			<Contact>
																				<xsl:for-each select="ContactID">
																					<ContactID>
																						<xsl:for-each select="Identifier">
																							<Identifier>
																								<xsl:for-each select="Agency">
																									<Agency>
																										<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																											<AgencyCoded>
																												<xsl:value-of select="."/>
																											</AgencyCoded>
																										</xsl:for-each>
																										<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																											<AgencyCodedOther>
																												<xsl:value-of select="."/>
																											</AgencyCodedOther>
																										</xsl:for-each>
																									</Agency>
																								</xsl:for-each>
																								<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																									<Ident>
																										<xsl:value-of select="."/>
																									</Ident>
																								</xsl:for-each>
																							</Identifier>
																						</xsl:for-each>
																					</ContactID>
																				</xsl:for-each>
																				<ContactName/>
																			</Contact>
																		</xsl:for-each>
																	</ListOfContact>
																</xsl:for-each>
															</OtherContacts>
														</xsl:for-each>
														<xsl:for-each select="CorrespondenceLanguage">
															<CorrespondenceLanguage>
																<xsl:for-each select="Language">
																	<Language>
																		<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<LanguageCoded>
																				<xsl:value-of select="."/>
																			</LanguageCoded>
																		</xsl:for-each>
																	</Language>
																</xsl:for-each>
															</CorrespondenceLanguage>
														</xsl:for-each>
													</Party>
												</xsl:for-each>
											</BillToParty>
										</xsl:for-each>
										<xsl:for-each select="RemitToParty">
											<RemitToParty>
												<xsl:for-each select="Party">
													<Party>
														<xsl:for-each select="PartyID">
															<PartyID>
																<xsl:for-each select="Identifier">
																	<Identifier>
																		<xsl:for-each select="Agency">
																			<Agency>
																				<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCoded>
																						<xsl:value-of select="."/>
																					</AgencyCoded>
																				</xsl:for-each>
																				<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCodedOther>
																						<xsl:value-of select="."/>
																					</AgencyCodedOther>
																				</xsl:for-each>
																			</Agency>
																		</xsl:for-each>
																		<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</xsl:for-each>
															</PartyID>
														</xsl:for-each>
														<xsl:for-each select="ListOfIdentifier">
															<ListOfIdentifier>
																<xsl:for-each select="Identifier">
																	<Identifier>
																		<Agency>
																			<xsl:for-each select="Agency/AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<AgencyCoded>
																					<xsl:value-of select="."/>
																				</AgencyCoded>
																			</xsl:for-each>
																			<xsl:for-each select="Agency/AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<AgencyCodedOther>
																					<xsl:value-of select="."/>
																				</AgencyCodedOther>
																			</xsl:for-each>
																		</Agency>
																		<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</xsl:for-each>
															</ListOfIdentifier>
														</xsl:for-each>
														<xsl:for-each select="NameAddress">
															<NameAddress>
																<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																	<Name1>
																		<xsl:value-of select="."/>
																	</Name1>
																</xsl:for-each>
																<xsl:for-each select="Name2/EDXBABLE/ORIGINATOR/VALUE">
																	<Name2>
																		<xsl:value-of select="."/>
																	</Name2>
																</xsl:for-each>
																<xsl:for-each select="Name3/EDXBABLE/ORIGINATOR/VALUE">
																	<Name3>
																		<xsl:value-of select="."/>
																	</Name3>
																</xsl:for-each>
																<xsl:for-each select="Street/EDXBABLE/ORIGINATOR/VALUE">
																	<Street>
																		<xsl:value-of select="."/>
																	</Street>
																</xsl:for-each>
																<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																	<POBox>
																		<xsl:value-of select="."/>
																	</POBox>
																</xsl:for-each>
																<xsl:for-each select="HouseNumber/EDXBABLE/ORIGINATOR/VALUE">
																	<HouseNumber>
																		<xsl:value-of select="."/>
																	</HouseNumber>
																</xsl:for-each>
																<xsl:for-each select="StreetSupplement1/EDXBABLE/ORIGINATOR/VALUE">
																	<StreetSupplement1>
																		<xsl:value-of select="."/>
																	</StreetSupplement1>
																</xsl:for-each>
																<xsl:for-each select="StreetSupplement2/EDXBABLE/ORIGINATOR/VALUE">
																	<StreetSupplement2>
																		<xsl:value-of select="."/>
																	</StreetSupplement2>
																</xsl:for-each>
																<xsl:for-each select="Floor/EDXBABLE/ORIGINATOR/VALUE">
																	<Floor>
																		<xsl:value-of select="."/>
																	</Floor>
																</xsl:for-each>
																<xsl:for-each select="PostalCode/EDXBABLE/ORIGINATOR/VALUE">
																	<PostalCode>
																		<xsl:value-of select="."/>
																	</PostalCode>
																</xsl:for-each>
																<xsl:for-each select="City/EDXBABLE/ORIGINATOR/VALUE">
																	<City>
																		<xsl:value-of select="."/>
																	</City>
																</xsl:for-each>
																<xsl:for-each select="County/EDXBABLE/ORIGINATOR/VALUE">
																	<County>
																		<xsl:value-of select="."/>
																	</County>
																</xsl:for-each>
																<xsl:for-each select="Region">
																	<Region>
																		<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<RegionCoded>
																				<xsl:value-of select="."/>
																			</RegionCoded>
																		</xsl:for-each>
																		<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																			<RegionCodedOther>
																				<xsl:value-of select="."/>
																			</RegionCodedOther>
																		</xsl:for-each>
																	</Region>
																</xsl:for-each>
																<xsl:for-each select="District/EDXBABLE/ORIGINATOR/VALUE">
																	<District>
																		<xsl:value-of select="."/>
																	</District>
																</xsl:for-each>
																<xsl:for-each select="Country">
																	<Country>
																		<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<CountryCoded>
																				<xsl:value-of select="."/>
																			</CountryCoded>
																		</xsl:for-each>
																	</Country>
																</xsl:for-each>
																<xsl:for-each select="Timezone">
																	<Timezone>
																		<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<TimezoneCoded>
																				<xsl:value-of select="."/>
																			</TimezoneCoded>
																		</xsl:for-each>
																	</Timezone>
																</xsl:for-each>
															</NameAddress>
														</xsl:for-each>
														<xsl:for-each select="OrderContact">
															<OrderContact>
																<xsl:for-each select="Contact">
																	<Contact>
																		<xsl:for-each select="ContactID">
																			<ContactID>
																				<xsl:for-each select="Identifier">
																					<Identifier>
																						<xsl:for-each select="Agency">
																							<Agency>
																								<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																									<AgencyCoded>
																										<xsl:value-of select="."/>
																									</AgencyCoded>
																								</xsl:for-each>
																								<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																									<AgencyCodedOther>
																										<xsl:value-of select="."/>
																									</AgencyCodedOther>
																								</xsl:for-each>
																							</Agency>
																						</xsl:for-each>
																						<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																							<Ident>
																								<xsl:value-of select="."/>
																							</Ident>
																						</xsl:for-each>
																					</Identifier>
																				</xsl:for-each>
																			</ContactID>
																		</xsl:for-each>
																		<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																			<ContactName>
																				<xsl:value-of select="."/>
																			</ContactName>
																		</xsl:for-each>
																		<xsl:for-each select="ContactFunction">
																			<ContactFunction>
																				<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<ContactFunctionCoded>
																						<xsl:value-of select="."/>
																					</ContactFunctionCoded>
																				</xsl:for-each>
																			</ContactFunction>
																		</xsl:for-each>
																		<xsl:for-each select="ListOfContactNumber">
																			<ListOfContactNumber>
																				<xsl:for-each select="ContactNumber">
																					<ContactNumber>
																						<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactNumberValue>
																								<xsl:value-of select="."/>
																							</ContactNumberValue>
																						</xsl:for-each>
																						<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactNumberTypeCoded>
																								<xsl:value-of select="."/>
																							</ContactNumberTypeCoded>
																						</xsl:for-each>
																					</ContactNumber>
																				</xsl:for-each>
																			</ListOfContactNumber>
																		</xsl:for-each>
																	</Contact>
																</xsl:for-each>
															</OrderContact>
														</xsl:for-each>
														<xsl:for-each select="OtherContacts">
															<OtherContacts>
																<xsl:for-each select="ListOfContact">
																	<ListOfContact>
																		<xsl:for-each select="Contact">
																			<Contact>
																				<xsl:for-each select="ContactID">
																					<ContactID>
																						<xsl:for-each select="Identifier">
																							<Identifier>
																								<xsl:for-each select="Agency">
																									<Agency>
																										<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																											<AgencyCoded>
																												<xsl:value-of select="."/>
																											</AgencyCoded>
																										</xsl:for-each>
																										<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																											<AgencyCodedOther>
																												<xsl:value-of select="."/>
																											</AgencyCodedOther>
																										</xsl:for-each>
																									</Agency>
																								</xsl:for-each>
																								<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																									<Ident>
																										<xsl:value-of select="."/>
																									</Ident>
																								</xsl:for-each>
																							</Identifier>
																						</xsl:for-each>
																					</ContactID>
																				</xsl:for-each>
																				<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																					<ContactName>
																						<xsl:value-of select="."/>
																					</ContactName>
																				</xsl:for-each>
																				<xsl:for-each select="ContactFunction">
																					<ContactFunction>
																						<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactFunctionCoded>
																								<xsl:value-of select="."/>
																							</ContactFunctionCoded>
																						</xsl:for-each>
																					</ContactFunction>
																				</xsl:for-each>
																				<xsl:for-each select="ListOfContactNumber">
																					<ListOfContactNumber>
																						<xsl:for-each select="ContactNumber">
																							<ContactNumber>
																								<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																									<ContactNumberValue>
																										<xsl:value-of select="."/>
																									</ContactNumberValue>
																								</xsl:for-each>
																								<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																									<ContactNumberTypeCoded>
																										<xsl:value-of select="."/>
																									</ContactNumberTypeCoded>
																								</xsl:for-each>
																							</ContactNumber>
																						</xsl:for-each>
																					</ListOfContactNumber>
																				</xsl:for-each>
																			</Contact>
																		</xsl:for-each>
																	</ListOfContact>
																</xsl:for-each>
															</OtherContacts>
														</xsl:for-each>
														<xsl:for-each select="CorrespondenceLanguage">
															<CorrespondenceLanguage>
																<xsl:for-each select="Language">
																	<Language>
																		<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<LanguageCoded>
																				<xsl:value-of select="."/>
																			</LanguageCoded>
																		</xsl:for-each>
																	</Language>
																</xsl:for-each>
															</CorrespondenceLanguage>
														</xsl:for-each>
													</Party>
												</xsl:for-each>
											</RemitToParty>
										</xsl:for-each>
									</OrderParty>
								</xsl:for-each>
								<ListOfTransport>
									<Transport>
										<TransportID>0</TransportID>
										<TransportMode>
											<TransportModeCoded>Other</TransportModeCoded>
											<TransportModeCodedOther>SEA FREIGHT</TransportModeCodedOther>
										</TransportMode>
										<CarrierID>
											<Identifier>
												<Agency>
													<AgencyCoded>AssignedBySellerOrSellersAgent</AgencyCoded>
													<AgencyCodedOther/>
												</Agency>
												<Ident></Ident>
											</Identifier>
										</CarrierID>
										<ShippingInstructions></ShippingInstructions>
										<TransitDirection>
											<TransitDirectionCoded>SellerToBuyer</TransitDirectionCoded>
										</TransitDirection>
									</Transport>
									<Transport>
										<TransportID>1</TransportID>
										<TransportMode>
											<TransportModeCoded>Other</TransportModeCoded>
											<TransportModeCodedOther></TransportModeCodedOther>
										</TransportMode>
										<CarrierID>
											<Identifier>
												<Agency>
													<AgencyCoded>AssignedBySellerOrSellersAgent</AgencyCoded>
												</Agency>
												<Ident></Ident>
											</Identifier>
										</CarrierID>
										<ShippingInstructions></ShippingInstructions>
										<TransitDirection>
											<TransitDirectionCoded>Other</TransitDirectionCoded>
										</TransitDirection>
									</Transport>
									<Transport>
										<TransportID>2</TransportID>
										<TransportMode>
											<TransportModeCoded>Other</TransportModeCoded>
											<TransportModeCodedOther></TransportModeCodedOther>
										</TransportMode>
										<CarrierID>
											<Identifier>
												<Agency>
													<AgencyCoded>AssignedBySellerOrSellersAgent</AgencyCoded>
												</Agency>
												<Ident></Ident>
											</Identifier>
										</CarrierID>
										<ShippingInstructions></ShippingInstructions>
										<TransitDirection>
											<TransitDirectionCoded>Other</TransitDirectionCoded>
										</TransitDirection>
									</Transport>
								</ListOfTransport>
								<xsl:for-each select="OrderHeaderNote/EDXBABLE/ORIGINATOR/VALUE">
									<OrderHeaderNote>
										<xsl:value-of select="."/>
									</OrderHeaderNote>
								</xsl:for-each>
								<xsl:for-each select="ListOfStructuredNote">
									<ListOfStructuredNote>
										<xsl:for-each select="StructuredNote">
											<StructuredNote>
												<xsl:for-each select="GeneralNote/EDXBABLE/ORIGINATOR/VALUE">
													<GeneralNote>
														<xsl:value-of select="."/>
													</GeneralNote>
												</xsl:for-each>
												<xsl:for-each select="NoteID/EDXBABLE/ORIGINATOR/VALUE">
													<NoteID>
														<xsl:value-of select="."/>
													</NoteID>
												</xsl:for-each>
												<Agency>
													<xsl:for-each select="Agency/AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
														<AgencyCoded>
															<xsl:value-of select="."/>
														</AgencyCoded>
													</xsl:for-each>
													<xsl:for-each select="Agency/AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
														<AgencyCodedOther>
															<xsl:value-of select="."/>
														</AgencyCodedOther>
													</xsl:for-each>
												</Agency>
											</StructuredNote>
										</xsl:for-each>
									</ListOfStructuredNote>
								</xsl:for-each>
								<OrderHeaderAttachments>
									<ListOfAttachment>
										<Attachment>
											<AttachmentPurpose>xcbl/30</AttachmentPurpose>
											<FileName>xcbl/30</FileName>
											<ReplacementFile>false</ReplacementFile>
											<AttachmentLocation>urn:x-commerceone:package:com:commerceone:xcbl/30</AttachmentLocation>
										</Attachment>
									</ListOfAttachment>
								</OrderHeaderAttachments>
							</OrderHeader>
						</xsl:for-each>
					</OriginalOrderHeader>
          <!--Ignore the change order sections 1-->
					<xsl:for-each select="ChangeOrderHeader"/>
          <!--Ignore the OrderHeaderChanges  sections-->
					<xsl:for-each select="OrderHeaderChanges">
						<OrderHeaderChanges>
							<xsl:for-each select=".">
								<OrderHeader>
									<xsl:for-each select="OrderNumber">
										<OrderNumber>
											<xsl:for-each select="BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
												<BuyerOrderNumber>
													<xsl:value-of select="."/>
												</BuyerOrderNumber>
											</xsl:for-each>
											<xsl:for-each select="SellerOrderNumber/EDXBABLE/DESTINATION/RESOLVEDVALUE">
												<SellerOrderNumber>
													<xsl:value-of select="."/>
												</SellerOrderNumber>
											</xsl:for-each>
										</OrderNumber>
									</xsl:for-each>
									<xsl:for-each select="OrderIssueDate/EDXBABLE/ORIGINATOR/VALUE">
										<OrderIssueDate>
											<xsl:value-of select="."/>
										</OrderIssueDate>
									</xsl:for-each>
									<xsl:for-each select="OrderReferences">
										<OrderReferences>
											<xsl:for-each select="AccountCode">
												<AccountCode>
													<xsl:for-each select="Reference">
														<Reference>
															<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																<RefNum>
																	<xsl:value-of select="."/>
																</RefNum>
															</xsl:for-each>
														</Reference>
													</xsl:for-each>
												</AccountCode>
											</xsl:for-each>
											<xsl:for-each select="OtherOrderReferences">
												<OtherOrderReferences>
													<xsl:for-each select="ListOfReferenceCoded">
														<ListOfReferenceCoded>
															<xsl:for-each select="ReferenceCoded">
																<ReferenceCoded>
																	<xsl:for-each select="ReferenceTypeCoded">
																		<ReferenceTypeCoded>Other
																			<xsl:value-of select="."/>
																		</ReferenceTypeCoded>
																	</xsl:for-each>
																	<xsl:for-each select="ReferenceTypeCodedOther">
																		<ReferenceTypeCodedOther>
																			<xsl:value-of select="."/>
																		</ReferenceTypeCodedOther>
																	</xsl:for-each>
																	<xsl:for-each select="PrimaryReference">
																		<PrimaryReference>
																			<xsl:for-each select="Reference">
																				<Reference>
																					<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																						<RefNum>
																							<xsl:value-of select="."/>
																						</RefNum>
																					</xsl:for-each>
																					<xsl:for-each select="RefDate/EDXBABLE/ORIGINATOR/VALUE">
																						<RefDate>
																							<xsl:value-of select="."/>
																						</RefDate>
																					</xsl:for-each>
																				</Reference>
																			</xsl:for-each>
																		</PrimaryReference>
																	</xsl:for-each>
																</ReferenceCoded>
															</xsl:for-each>
														</ListOfReferenceCoded>
													</xsl:for-each>
												</OtherOrderReferences>
											</xsl:for-each>
										</OrderReferences>
									</xsl:for-each>
									<xsl:for-each select="Purpose">
										<Purpose>
											<xsl:for-each select="PurposeCoded/EDXBABLE/ORIGINATOR/VALUE">
												<PurposeCoded>
													<xsl:value-of select="."/>
												</PurposeCoded>
											</xsl:for-each>
										</Purpose>
									</xsl:for-each>
									<xsl:for-each select="RequestedResponse">
										<RequestedResponse>
											<xsl:for-each select="RequestedResponseCoded/EDXBABLE/ORIGINATOR/VALUE">
												<RequestedResponseCoded>
													<xsl:value-of select="."/>
												</RequestedResponseCoded>
											</xsl:for-each>
										</RequestedResponse>
									</xsl:for-each>
									<xsl:for-each select="OrderType">
										<OrderType>
											<xsl:for-each select="OrderTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
												<OrderTypeCoded>
													<xsl:value-of select="."/>
												</OrderTypeCoded>
											</xsl:for-each>
										</OrderType>
									</xsl:for-each>
									<xsl:for-each select="OrderCurrency">
										<OrderCurrency>
											<xsl:for-each select="Currency">
												<Currency>
													<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
														<CurrencyCoded>
															<xsl:value-of select="."/>
														</CurrencyCoded>
													</xsl:for-each>
												</Currency>
											</xsl:for-each>
										</OrderCurrency>
									</xsl:for-each>
									<xsl:for-each select="OrderLanguage">
										<OrderLanguage>
											<xsl:for-each select="Language">
												<Language>
													<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
														<LanguageCoded>
															<xsl:value-of select="."/>
														</LanguageCoded>
													</xsl:for-each>
												</Language>
											</xsl:for-each>
										</OrderLanguage>
									</xsl:for-each>
									<xsl:for-each select="OrderDates">
										<OrderDates>
											<xsl:for-each select="ListOfDateCoded">
												<ListOfDateCoded>
													<xsl:for-each select="DateCoded/EDXBABLE/ORIGINATOR/VALUE">
														<DateCoded>
															<xsl:for-each select="../../../Date/EDXBABLE/ORIGINATOR/VALUE">
																<Date>
																	<xsl:value-of select="."/>
																</Date>
															</xsl:for-each>
															<DateQualifier>
																<xsl:for-each select="../../../DateQualifier/DateQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																	<DateQualifierCoded>
																		<xsl:value-of select="."/>
																	</DateQualifierCoded>
																</xsl:for-each>
															</DateQualifier>
															<xsl:value-of select="."/>
														</DateCoded>
													</xsl:for-each>
												</ListOfDateCoded>
											</xsl:for-each>
										</OrderDates>
									</xsl:for-each>
									<xsl:for-each select="OrderParty">
										<OrderParty>
											<xsl:for-each select="BuyerParty">
												<BuyerParty>
													<xsl:for-each select="Party">
														<Party>
															<xsl:for-each select="PartyID">
																<PartyID>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</PartyID>
															</xsl:for-each>
															<xsl:for-each select="ListOfIdentifier">
																<ListOfIdentifier>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</ListOfIdentifier>
															</xsl:for-each>
															<xsl:for-each select="NameAddress">
																<NameAddress>
																	<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																		<Name1>
																			<xsl:value-of select="."/>
																		</Name1>
																	</xsl:for-each>
																	<xsl:for-each select="Name2/EDXBABLE/ORIGINATOR/VALUE">
																		<Name2>
																			<xsl:value-of select="."/>
																		</Name2>
																	</xsl:for-each>
																	<xsl:for-each select="Name3/EDXBABLE/ORIGINATOR/VALUE">
																		<Name3>
																			<xsl:value-of select="."/>
																		</Name3>
																	</xsl:for-each>
																	<xsl:for-each select="POBox">
																		<POBox/>
																	</xsl:for-each>
																	<xsl:for-each select="Street/EDXBABLE/ORIGINATOR/VALUE">
																		<Street>
																			<xsl:value-of select="."/>
																		</Street>
																	</xsl:for-each>
																	<xsl:for-each select="HouseNumber/EDXBABLE/ORIGINATOR/VALUE">
																		<HouseNumber>
																			<xsl:value-of select="."/>
																		</HouseNumber>
																	</xsl:for-each>
																	<xsl:for-each select="StreetSupplement1/EDXBABLE/ORIGINATOR/VALUE">
																		<StreetSupplement1>
																			<xsl:value-of select="."/>
																		</StreetSupplement1>
																	</xsl:for-each>
																	<xsl:for-each select="StreetSupplement2/EDXBABLE/ORIGINATOR/VALUE">
																		<StreetSupplement2>
																			<xsl:value-of select="."/>
																		</StreetSupplement2>
																	</xsl:for-each>
																	<xsl:for-each select="Building/EDXBABLE/ORIGINATOR/VALUE">
																		<Building>
																			<xsl:value-of select="."/>
																		</Building>
																	</xsl:for-each>
																	<xsl:for-each select="Floor/EDXBABLE/ORIGINATOR/VALUE">
																		<Floor>
																			<xsl:value-of select="."/>
																		</Floor>
																	</xsl:for-each>
																	<xsl:for-each select="PostalCode/EDXBABLE/ORIGINATOR/VALUE">
																		<PostalCode>
																			<xsl:value-of select="."/>
																		</PostalCode>
																	</xsl:for-each>
																	<xsl:for-each select="City/EDXBABLE/ORIGINATOR/VALUE">
																		<City>
																			<xsl:value-of select="."/>
																		</City>
																	</xsl:for-each>
																	<xsl:for-each select="Region">
																		<Region>
																			<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<RegionCoded>
																					<xsl:value-of select="."/>
																				</RegionCoded>
																			</xsl:for-each>
																			<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<RegionCodedOther>
																					<xsl:value-of select="."/>
																				</RegionCodedOther>
																			</xsl:for-each>
																		</Region>
																	</xsl:for-each>
																	<xsl:for-each select="Country">
																		<Country>
																			<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<CountryCoded>
																					<xsl:value-of select="."/>
																				</CountryCoded>
																			</xsl:for-each>
																			<xsl:for-each select="CountryCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<CountryCodedOther>
																					<xsl:value-of select="."/>
																				</CountryCodedOther>
																			</xsl:for-each>
																		</Country>
																	</xsl:for-each>
																	<xsl:for-each select="Timezone">
																		<Timezone>
																			<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<TimezoneCoded>
																					<xsl:value-of select="."/>
																				</TimezoneCoded>
																			</xsl:for-each>
																			<xsl:for-each select="TimezoneCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<TimezoneCodedOther>
																					<xsl:value-of select="."/>
																				</TimezoneCodedOther>
																			</xsl:for-each>
																		</Timezone>
																	</xsl:for-each>
																</NameAddress>
															</xsl:for-each>
															<xsl:for-each select="OrderContact">
																<OrderContact>
																	<xsl:for-each select="Contact">
																		<Contact>
																			<xsl:for-each select="ContactID">
																				<ContactID>
																					<xsl:for-each select="Identifier">
																						<Identifier>
																							<xsl:for-each select="Agency">
																								<Agency>
																									<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCoded>
																											<xsl:value-of select="."/>
																										</AgencyCoded>
																									</xsl:for-each>
																									<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCodedOther>
																											<xsl:value-of select="."/>
																										</AgencyCodedOther>
																									</xsl:for-each>
																								</Agency>
																							</xsl:for-each>
																							<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</xsl:for-each>
																				</ContactID>
																			</xsl:for-each>
																			<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																				<ContactName>
																					<xsl:value-of select="."/>
																				</ContactName>
																			</xsl:for-each>
																			<xsl:for-each select="ContactFunction">
																				<ContactFunction>
																					<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactFunctionCoded>
																							<xsl:value-of select="."/>
																						</ContactFunctionCoded>
																					</xsl:for-each>
																				</ContactFunction>
																			</xsl:for-each>
																			<xsl:for-each select="ListOfContactNumber">
																				<ListOfContactNumber>
																					<xsl:for-each select="ContactNumber">
																						<ContactNumber>
																							<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactNumberValue>
																									<xsl:value-of select="."/>
																								</ContactNumberValue>
																							</xsl:for-each>
																							<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactNumberTypeCoded>
																									<xsl:value-of select="."/>
																								</ContactNumberTypeCoded>
																							</xsl:for-each>
																						</ContactNumber>
																					</xsl:for-each>
																				</ListOfContactNumber>
																			</xsl:for-each>
																		</Contact>
																	</xsl:for-each>
																</OrderContact>
															</xsl:for-each>
															<xsl:for-each select="OtherContacts">
																<OtherContacts>
																	<xsl:for-each select="ListOfContact">
																		<ListOfContact>
																			<xsl:for-each select="Contact">
																				<Contact>
																					<xsl:for-each select="ContactID">
																						<ContactID>
																							<xsl:for-each select="Identifier">
																								<Identifier>
																									<xsl:for-each select="Agency">
																										<Agency>
																											<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCoded>
																													<xsl:value-of select="."/>
																												</AgencyCoded>
																											</xsl:for-each>
																											<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCodedOther>
																													<xsl:value-of select="."/>
																												</AgencyCodedOther>
																											</xsl:for-each>
																										</Agency>
																									</xsl:for-each>
																									<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																										<Ident>
																											<xsl:value-of select="."/>
																										</Ident>
																									</xsl:for-each>
																								</Identifier>
																							</xsl:for-each>
																						</ContactID>
																					</xsl:for-each>
																					<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactName>
																							<xsl:value-of select="."/>
																						</ContactName>
																					</xsl:for-each>
																					<xsl:for-each select="ContactFunction">
																						<ContactFunction>
																							<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactFunctionCoded>
																									<xsl:value-of select="."/>
																								</ContactFunctionCoded>
																							</xsl:for-each>
																						</ContactFunction>
																					</xsl:for-each>
																					<xsl:for-each select="ListOfContactNumber">
																						<ListOfContactNumber>
																							<xsl:for-each select="ContactNumber">
																								<ContactNumber>
																									<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																										<ContactNumberValue>
																											<xsl:value-of select="."/>
																										</ContactNumberValue>
																									</xsl:for-each>
																									<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<ContactNumberTypeCoded>
																											<xsl:value-of select="."/>
																										</ContactNumberTypeCoded>
																									</xsl:for-each>
																								</ContactNumber>
																							</xsl:for-each>
																						</ListOfContactNumber>
																					</xsl:for-each>
																				</Contact>
																			</xsl:for-each>
																		</ListOfContact>
																	</xsl:for-each>
																</OtherContacts>
															</xsl:for-each>
															<xsl:for-each select="CorrespondenceLanguage">
																<CorrespondenceLanguage>
																	<xsl:for-each select="Language">
																		<Language>
																			<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<LanguageCoded>
																					<xsl:value-of select="."/>
																				</LanguageCoded>
																			</xsl:for-each>
																		</Language>
																	</xsl:for-each>
																</CorrespondenceLanguage>
															</xsl:for-each>
														</Party>
													</xsl:for-each>
												</BuyerParty>
											</xsl:for-each>
											<xsl:for-each select="BuyerTaxInformation">
												<BuyerTaxInformation>
													<xsl:for-each select="PartyTaxInformation">
														<PartyTaxInformation>
															<xsl:for-each select="RegisteredName/EDXBABLE/ORIGINATOR/VALUE">
																<RegisteredName>
																	<xsl:value-of select="."/>
																</RegisteredName>
															</xsl:for-each>
															<xsl:for-each select="CompanyRegistrationNumber/EDXBABLE/ORIGINATOR/VALUE">
																<CompanyRegistrationNumber>
																	<xsl:value-of select="."/>
																</CompanyRegistrationNumber>
															</xsl:for-each>
														</PartyTaxInformation>
													</xsl:for-each>
												</BuyerTaxInformation>
											</xsl:for-each>
											<xsl:for-each select="SellerParty">
												<SellerParty>
													<xsl:for-each select="Party">
														<Party>
															<xsl:for-each select="PartyID">
																<PartyID>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</PartyID>
															</xsl:for-each>
															<xsl:for-each select="ListOfIdentifier">
																<ListOfIdentifier>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</ListOfIdentifier>
															</xsl:for-each>
															<NameAddress>
																<Name1/>
																<Region/>
																<Country>
																	<CountryCoded/>
																</Country>
															</NameAddress>
															<OrderContact>
																<Contact>
																	<ContactID>
																		<Identifier>
																			<Agency>
																				<AgencyCoded/>
																			</Agency>
																			<Ident/>
																		</Identifier>
																	</ContactID>
																	<ContactName/>
																	<ListOfContactNumber>
																		<ContactNumber>
																			<ContactNumberValue/>
																			<ContactNumberTypeCoded/>
																		</ContactNumber>
																	</ListOfContactNumber>
																</Contact>
															</OrderContact>
															<xsl:for-each select="OtherContacts">
																<OtherContacts>
																	<xsl:for-each select="ListOfContact">
																		<ListOfContact>
																			<xsl:for-each select="Contact">
																				<Contact>
																					<xsl:for-each select="ContactID">
																						<ContactID>
																							<xsl:for-each select="Identifier">
																								<Identifier>
																									<xsl:for-each select="Agency">
																										<Agency>
																											<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCoded>
																													<xsl:value-of select="."/>
																												</AgencyCoded>
																											</xsl:for-each>
																											<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCodedOther>
																													<xsl:value-of select="."/>
																												</AgencyCodedOther>
																											</xsl:for-each>
																										</Agency>
																									</xsl:for-each>
																									<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																										<Ident>
																											<xsl:value-of select="."/>
																										</Ident>
																									</xsl:for-each>
																								</Identifier>
																							</xsl:for-each>
																						</ContactID>
																					</xsl:for-each>
																					<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactName>
																							<xsl:value-of select="."/>
																						</ContactName>
																					</xsl:for-each>
																					<xsl:for-each select="ContactFunction">
																						<ContactFunction>
																							<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactFunctionCoded>
																									<xsl:value-of select="."/>
																								</ContactFunctionCoded>
																							</xsl:for-each>
																						</ContactFunction>
																					</xsl:for-each>
																					<xsl:for-each select="ListOfContactNumber">
																						<ListOfContactNumber>
																							<xsl:for-each select="ContactNumber">
																								<ContactNumber>
																									<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																										<ContactNumberValue>
																											<xsl:value-of select="."/>
																										</ContactNumberValue>
																									</xsl:for-each>
																									<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<ContactNumberTypeCoded>
																											<xsl:value-of select="."/>
																										</ContactNumberTypeCoded>
																									</xsl:for-each>
																								</ContactNumber>
																							</xsl:for-each>
																						</ListOfContactNumber>
																					</xsl:for-each>
																				</Contact>
																			</xsl:for-each>
																		</ListOfContact>
																	</xsl:for-each>
																</OtherContacts>
															</xsl:for-each>
															<xsl:for-each select="CorrespondenceLanguage">
																<CorrespondenceLanguage>
																	<xsl:for-each select="Language">
																		<Language>
																			<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<LanguageCoded>
																					<xsl:value-of select="."/>
																				</LanguageCoded>
																			</xsl:for-each>
																		</Language>
																	</xsl:for-each>
																</CorrespondenceLanguage>
															</xsl:for-each>
														</Party>
													</xsl:for-each>
												</SellerParty>
											</xsl:for-each>
											<xsl:for-each select="ShipToParty">
												<ShipToParty>
													<xsl:for-each select="Party">
														<Party>
															<xsl:for-each select="PartyID">
																<PartyID>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</PartyID>
															</xsl:for-each>
															<xsl:for-each select="ListOfIdentifier">
																<ListOfIdentifier>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</ListOfIdentifier>
															</xsl:for-each>
															<xsl:for-each select="NameAddress">
																<NameAddress>
																	<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																		<Name1>
																			<xsl:value-of select="."/>
																		</Name1>
																	</xsl:for-each>
																	<xsl:for-each select="Name2/EDXBABLE/ORIGINATOR/VALUE">
																		<Name2>
																			<xsl:value-of select="."/>
																		</Name2>
																	</xsl:for-each>
																	<xsl:for-each select="Name3/EDXBABLE/ORIGINATOR/VALUE">
																		<Name3>
																			<xsl:value-of select="."/>
																		</Name3>
																	</xsl:for-each>
																	<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																		<POBox>
																			<xsl:value-of select="."/>
																		</POBox>
																	</xsl:for-each>
																	<xsl:for-each select="Street/EDXBABLE/ORIGINATOR/VALUE">
																		<Street>
																			<xsl:value-of select="."/>
																		</Street>
																	</xsl:for-each>
																	<xsl:for-each select="StreetSupplement1/EDXBABLE/ORIGINATOR/VALUE">
																		<StreetSupplement1>
																			<xsl:value-of select="."/>
																		</StreetSupplement1>
																	</xsl:for-each>
																	<xsl:for-each select="StreetSupplement2/EDXBABLE/ORIGINATOR/VALUE">
																		<StreetSupplement2>
																			<xsl:value-of select="."/>
																		</StreetSupplement2>
																	</xsl:for-each>
																	<xsl:for-each select="PostalCode/EDXBABLE/ORIGINATOR/VALUE">
																		<PostalCode>
																			<xsl:value-of select="."/>
																		</PostalCode>
																	</xsl:for-each>
																	<xsl:for-each select="City/EDXBABLE/ORIGINATOR/VALUE">
																		<City>
																			<xsl:value-of select="."/>
																		</City>
																	</xsl:for-each>
																	<xsl:for-each select="Region">
																		<Region>
																			<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<RegionCoded>
																					<xsl:value-of select="."/>
																				</RegionCoded>
																			</xsl:for-each>
																			<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<RegionCodedOther>
																					<xsl:value-of select="."/>
																				</RegionCodedOther>
																			</xsl:for-each>
																		</Region>
																	</xsl:for-each>
																	<xsl:for-each select="Country">
																		<Country>
																			<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<CountryCoded>
																					<xsl:value-of select="."/>
																				</CountryCoded>
																			</xsl:for-each>
																			<xsl:for-each select="CountryCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<CountryCodedOther>
																					<xsl:value-of select="."/>
																				</CountryCodedOther>
																			</xsl:for-each>
																		</Country>
																	</xsl:for-each>
																	<xsl:for-each select="Timezone">
																		<Timezone>
																			<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<TimezoneCoded>
																					<xsl:value-of select="."/>
																				</TimezoneCoded>
																			</xsl:for-each>
																			<xsl:for-each select="TimezoneCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<TimezoneCodedOther>
																					<xsl:value-of select="."/>
																				</TimezoneCodedOther>
																			</xsl:for-each>
																		</Timezone>
																	</xsl:for-each>
																</NameAddress>
															</xsl:for-each>
															<xsl:for-each select="OrderContact">
																<OrderContact>
																	<xsl:for-each select="Contact">
																		<Contact>
																			<xsl:for-each select="ContactID">
																				<ContactID>
																					<xsl:for-each select="Identifier">
																						<Identifier>
																							<xsl:for-each select="Agency">
																								<Agency>
																									<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCoded>
																											<xsl:value-of select="."/>
																										</AgencyCoded>
																									</xsl:for-each>
																									<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCodedOther>
																											<xsl:value-of select="."/>
																										</AgencyCodedOther>
																									</xsl:for-each>
																								</Agency>
																							</xsl:for-each>
																							<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</xsl:for-each>
																				</ContactID>
																			</xsl:for-each>
																			<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																				<ContactName>
																					<xsl:value-of select="."/>
																				</ContactName>
																			</xsl:for-each>
																			<xsl:for-each select="ContactFunction">
																				<ContactFunction>
																					<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactFunctionCoded>
																							<xsl:value-of select="."/>
																						</ContactFunctionCoded>
																					</xsl:for-each>
																					<xsl:for-each select="ContactFunctionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactFunctionCodedOther>
																							<xsl:value-of select="."/>
																						</ContactFunctionCodedOther>
																					</xsl:for-each>
																				</ContactFunction>
																			</xsl:for-each>
																			<xsl:for-each select="ListOfContactNumber">
																				<ListOfContactNumber>
																					<xsl:for-each select="ContactNumber">
																						<ContactNumber>
																							<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactNumberValue>
																									<xsl:value-of select="."/>
																								</ContactNumberValue>
																							</xsl:for-each>
																							<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactNumberTypeCoded>
																									<xsl:value-of select="."/>
																								</ContactNumberTypeCoded>
																							</xsl:for-each>
																						</ContactNumber>
																					</xsl:for-each>
																				</ListOfContactNumber>
																			</xsl:for-each>
																		</Contact>
																	</xsl:for-each>
																</OrderContact>
															</xsl:for-each>
															<xsl:for-each select="OtherContacts">
																<OtherContacts>
																	<xsl:for-each select="ListOfContact">
																		<ListOfContact>
																			<xsl:for-each select="Contact">
																				<Contact>
																					<xsl:for-each select="ContactID">
																						<ContactID>
																							<xsl:for-each select="Identifier">
																								<Identifier>
																									<xsl:for-each select="Agency">
																										<Agency>
																											<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCoded>
																													<xsl:value-of select="."/>
																												</AgencyCoded>
																											</xsl:for-each>
																											<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCodedOther>
																													<xsl:value-of select="."/>
																												</AgencyCodedOther>
																											</xsl:for-each>
																										</Agency>
																									</xsl:for-each>
																									<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																										<Ident>
																											<xsl:value-of select="."/>
																										</Ident>
																									</xsl:for-each>
																								</Identifier>
																							</xsl:for-each>
																						</ContactID>
																					</xsl:for-each>
																					<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactName>
																							<xsl:value-of select="."/>
																						</ContactName>
																					</xsl:for-each>
																					<xsl:for-each select="ContactFunction">
																						<ContactFunction>
																							<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactFunctionCoded>
																									<xsl:value-of select="."/>
																								</ContactFunctionCoded>
																							</xsl:for-each>
																						</ContactFunction>
																					</xsl:for-each>
																					<xsl:for-each select="ListOfContactNumber">
																						<ListOfContactNumber>
																							<xsl:for-each select="ContactNumber">
																								<ContactNumber>
																									<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																										<ContactNumberValue>
																											<xsl:value-of select="."/>
																										</ContactNumberValue>
																									</xsl:for-each>
																									<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<ContactNumberTypeCoded>
																											<xsl:value-of select="."/>
																										</ContactNumberTypeCoded>
																									</xsl:for-each>
																								</ContactNumber>
																							</xsl:for-each>
																						</ListOfContactNumber>
																					</xsl:for-each>
																				</Contact>
																			</xsl:for-each>
																		</ListOfContact>
																	</xsl:for-each>
																</OtherContacts>
															</xsl:for-each>
															<xsl:for-each select="CorrespondenceLanguage">
																<CorrespondenceLanguage>
																	<xsl:for-each select="Language">
																		<Language>
																			<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<LanguageCoded>
																					<xsl:value-of select="."/>
																				</LanguageCoded>
																			</xsl:for-each>
																		</Language>
																	</xsl:for-each>
																</CorrespondenceLanguage>
															</xsl:for-each>
														</Party>
													</xsl:for-each>
												</ShipToParty>
											</xsl:for-each>
											<xsl:for-each select="BillToParty">
												<BillToParty>
													<xsl:for-each select="Party">
														<Party>
															<xsl:for-each select="PartyID">
																<PartyID>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</PartyID>
															</xsl:for-each>
															<xsl:for-each select="ListOfIdentifier">
																<ListOfIdentifier>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</ListOfIdentifier>
															</xsl:for-each>
															<xsl:for-each select="NameAddress">
																<NameAddress>
																	<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																		<Name1>
																			<xsl:value-of select="."/>
																		</Name1>
																	</xsl:for-each>
																	<xsl:for-each select="Name2/EDXBABLE/ORIGINATOR/VALUE">
																		<Name2>
																			<xsl:value-of select="."/>
																		</Name2>
																	</xsl:for-each>
																	<xsl:for-each select="Name3/EDXBABLE/ORIGINATOR/VALUE">
																		<Name3>
																			<xsl:value-of select="."/>
																		</Name3>
																	</xsl:for-each>
																	<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																		<POBox>
																			<xsl:value-of select="."/>
																		</POBox>
																	</xsl:for-each>
																	<xsl:for-each select="Street/EDXBABLE/ORIGINATOR/VALUE">
																		<Street>
																			<xsl:value-of select="."/>
																		</Street>
																	</xsl:for-each>
																	<xsl:for-each select="StreetSupplement1/EDXBABLE/ORIGINATOR/VALUE">
																		<StreetSupplement1>
																			<xsl:value-of select="."/>
																		</StreetSupplement1>
																	</xsl:for-each>
																	<xsl:for-each select="StreetSupplement2/EDXBABLE/ORIGINATOR/VALUE">
																		<StreetSupplement2>
																			<xsl:value-of select="."/>
																		</StreetSupplement2>
																	</xsl:for-each>
																	<xsl:for-each select="PostalCode/EDXBABLE/ORIGINATOR/VALUE">
																		<PostalCode>
																			<xsl:value-of select="."/>
																		</PostalCode>
																	</xsl:for-each>
																	<xsl:for-each select="City/EDXBABLE/ORIGINATOR/VALUE">
																		<City>
																			<xsl:value-of select="."/>
																		</City>
																	</xsl:for-each>
																	<xsl:for-each select="Region">
																		<Region>
																			<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<RegionCoded>
																					<xsl:value-of select="."/>
																				</RegionCoded>
																			</xsl:for-each>
																			<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<RegionCodedOther>
																					<xsl:value-of select="."/>
																				</RegionCodedOther>
																			</xsl:for-each>
																		</Region>
																	</xsl:for-each>
																	<xsl:for-each select="Country">
																		<Country>
																			<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<CountryCoded>
																					<xsl:value-of select="."/>
																				</CountryCoded>
																			</xsl:for-each>
																			<xsl:for-each select="CountryCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<CountryCodedOther>
																					<xsl:value-of select="."/>
																				</CountryCodedOther>
																			</xsl:for-each>
																		</Country>
																	</xsl:for-each>
																	<xsl:for-each select="Timezone">
																		<Timezone>
																			<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<TimezoneCoded>
																					<xsl:value-of select="."/>
																				</TimezoneCoded>
																			</xsl:for-each>
																			<xsl:for-each select="TimezoneCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<TimezoneCodedOther>
																					<xsl:value-of select="."/>
																				</TimezoneCodedOther>
																			</xsl:for-each>
																		</Timezone>
																	</xsl:for-each>
																</NameAddress>
															</xsl:for-each>
															<xsl:for-each select="OrderContact">
																<OrderContact>
																	<xsl:for-each select="Contact">
																		<Contact>
																			<xsl:for-each select="ContactID">
																				<ContactID>
																					<xsl:for-each select="Identifier">
																						<Identifier>
																							<xsl:for-each select="Agency">
																								<Agency>
																									<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCoded>
																											<xsl:value-of select="."/>
																										</AgencyCoded>
																									</xsl:for-each>
																									<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCodedOther>
																											<xsl:value-of select="."/>
																										</AgencyCodedOther>
																									</xsl:for-each>
																								</Agency>
																							</xsl:for-each>
																							<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</xsl:for-each>
																				</ContactID>
																			</xsl:for-each>
																			<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																				<ContactName>
																					<xsl:value-of select="."/>
																				</ContactName>
																			</xsl:for-each>
																			<xsl:for-each select="ContactFunction">
																				<ContactFunction>
																					<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactFunctionCoded>
																							<xsl:value-of select="."/>
																						</ContactFunctionCoded>
																					</xsl:for-each>
																				</ContactFunction>
																			</xsl:for-each>
																			<xsl:for-each select="ListOfContactNumber">
																				<ListOfContactNumber>
																					<xsl:for-each select="ContactNumber">
																						<ContactNumber>
																							<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactNumberValue>
																									<xsl:value-of select="."/>
																								</ContactNumberValue>
																							</xsl:for-each>
																							<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactNumberTypeCoded>
																									<xsl:value-of select="."/>
																								</ContactNumberTypeCoded>
																							</xsl:for-each>
																						</ContactNumber>
																					</xsl:for-each>
																				</ListOfContactNumber>
																			</xsl:for-each>
																		</Contact>
																	</xsl:for-each>
																</OrderContact>
															</xsl:for-each>
															<xsl:for-each select="OtherContacts">
																<OtherContacts>
																	<xsl:for-each select="ListOfContact">
																		<ListOfContact>
																			<xsl:for-each select="Contact">
																				<Contact>
																					<ContactID>
																						<Identifier>
																							<Agency>
																								<xsl:for-each select="ContactID/Identifier/Agency/AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																									<AgencyCoded>
																										<xsl:value-of select="."/>
																									</AgencyCoded>
																								</xsl:for-each>
																								<xsl:for-each select="ContactID/Identifier/Agency/AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																									<AgencyCodedOther>
																										<xsl:value-of select="."/>
																									</AgencyCodedOther>
																								</xsl:for-each>
																							</Agency>
																							<xsl:for-each select="ContactID/Identifier/Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</ContactID>
																					<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactName>
																							<xsl:value-of select="."/>
																						</ContactName>
																					</xsl:for-each>
																					<ContactFunction>
																						<xsl:for-each select="ContactFunction/ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																							<ContactFunctionCoded>
																								<xsl:value-of select="."/>
																							</ContactFunctionCoded>
																						</xsl:for-each>
																					</ContactFunction>
																					<xsl:for-each select="ListOfContactNumber">
																						<ListOfContactNumber>
																							<xsl:for-each select="ContactNumber">
																								<ContactNumber>
																									<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																										<ContactNumberValue>
																											<xsl:value-of select="."/>
																										</ContactNumberValue>
																									</xsl:for-each>
																									<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<ContactNumberTypeCoded>
																											<xsl:value-of select="."/>
																										</ContactNumberTypeCoded>
																									</xsl:for-each>
																								</ContactNumber>
																							</xsl:for-each>
																						</ListOfContactNumber>
																					</xsl:for-each>
																				</Contact>
																			</xsl:for-each>
																		</ListOfContact>
																	</xsl:for-each>
																</OtherContacts>
															</xsl:for-each>
															<xsl:for-each select="CorrespondenceLanguage">
																<CorrespondenceLanguage>
																	<xsl:for-each select="Language">
																		<Language>
																			<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<LanguageCoded>
																					<xsl:value-of select="."/>
																				</LanguageCoded>
																			</xsl:for-each>
																		</Language>
																	</xsl:for-each>
																</CorrespondenceLanguage>
															</xsl:for-each>
														</Party>
													</xsl:for-each>
												</BillToParty>
											</xsl:for-each>
											<xsl:for-each select="RemitToParty">
												<RemitToParty>
													<xsl:for-each select="Party">
														<Party>
															<xsl:for-each select="PartyID">
																<PartyID>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</PartyID>
															</xsl:for-each>
															<xsl:for-each select="ListOfIdentifier">
																<ListOfIdentifier>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<Agency>
																				<xsl:for-each select="Agency/AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCoded>
																						<xsl:value-of select="."/>
																					</AgencyCoded>
																				</xsl:for-each>
																				<xsl:for-each select="Agency/AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																					<AgencyCodedOther>
																						<xsl:value-of select="."/>
																					</AgencyCodedOther>
																				</xsl:for-each>
																			</Agency>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</ListOfIdentifier>
															</xsl:for-each>
															<xsl:for-each select="NameAddress">
																<NameAddress>
																	<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																		<Name1>
																			<xsl:value-of select="."/>
																		</Name1>
																	</xsl:for-each>
																	<xsl:for-each select="Name2/EDXBABLE/ORIGINATOR/VALUE">
																		<Name2>
																			<xsl:value-of select="."/>
																		</Name2>
																	</xsl:for-each>
																	<xsl:for-each select="Name3/EDXBABLE/ORIGINATOR/VALUE">
																		<Name3>
																			<xsl:value-of select="."/>
																		</Name3>
																	</xsl:for-each>
																	<xsl:for-each select="Street/EDXBABLE/ORIGINATOR/VALUE">
																		<Street>
																			<xsl:value-of select="."/>
																		</Street>
																	</xsl:for-each>
																	<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																		<POBox>
																			<xsl:value-of select="."/>
																		</POBox>
																	</xsl:for-each>
																	<xsl:for-each select="HouseNumber/EDXBABLE/ORIGINATOR/VALUE">
																		<HouseNumber>
																			<xsl:value-of select="."/>
																		</HouseNumber>
																	</xsl:for-each>
																	<xsl:for-each select="StreetSupplement1/EDXBABLE/ORIGINATOR/VALUE">
																		<StreetSupplement1>
																			<xsl:value-of select="."/>
																		</StreetSupplement1>
																	</xsl:for-each>
																	<xsl:for-each select="StreetSupplement2/EDXBABLE/ORIGINATOR/VALUE">
																		<StreetSupplement2>
																			<xsl:value-of select="."/>
																		</StreetSupplement2>
																	</xsl:for-each>
																	<xsl:for-each select="Floor/EDXBABLE/ORIGINATOR/VALUE">
																		<Floor>
																			<xsl:value-of select="."/>
																		</Floor>
																	</xsl:for-each>
																	<xsl:for-each select="PostalCode/EDXBABLE/ORIGINATOR/VALUE">
																		<PostalCode>
																			<xsl:value-of select="."/>
																		</PostalCode>
																	</xsl:for-each>
																	<xsl:for-each select="City/EDXBABLE/ORIGINATOR/VALUE">
																		<City>
																			<xsl:value-of select="."/>
																		</City>
																	</xsl:for-each>
																	<xsl:for-each select="County/EDXBABLE/ORIGINATOR/VALUE">
																		<County>
																			<xsl:value-of select="."/>
																		</County>
																	</xsl:for-each>
																	<xsl:for-each select="Region">
																		<Region>
																			<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<RegionCoded>
																					<xsl:value-of select="."/>
																				</RegionCoded>
																			</xsl:for-each>
																			<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<RegionCodedOther>
																					<xsl:value-of select="."/>
																				</RegionCodedOther>
																			</xsl:for-each>
																		</Region>
																	</xsl:for-each>
																	<xsl:for-each select="District/EDXBABLE/ORIGINATOR/VALUE">
																		<District>
																			<xsl:value-of select="."/>
																		</District>
																	</xsl:for-each>
																	<xsl:for-each select="Country">
																		<Country>
																			<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<CountryCoded>
																					<xsl:value-of select="."/>
																				</CountryCoded>
																			</xsl:for-each>
																			<xsl:for-each select="CountryCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<CountryCodedOther>
																					<xsl:value-of select="."/>
																				</CountryCodedOther>
																			</xsl:for-each>
																		</Country>
																	</xsl:for-each>
																	<xsl:for-each select="Timezone">
																		<Timezone>
																			<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<TimezoneCoded>
																					<xsl:value-of select="."/>
																				</TimezoneCoded>
																			</xsl:for-each>
																		</Timezone>
																	</xsl:for-each>
																</NameAddress>
															</xsl:for-each>
															<xsl:for-each select="OrderContact">
																<OrderContact>
																	<xsl:for-each select="Contact">
																		<Contact>
																			<xsl:for-each select="ContactID">
																				<ContactID>
																					<xsl:for-each select="Identifier">
																						<Identifier>
																							<xsl:for-each select="Agency">
																								<Agency>
																									<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCoded>
																											<xsl:value-of select="."/>
																										</AgencyCoded>
																									</xsl:for-each>
																									<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCodedOther>
																											<xsl:value-of select="."/>
																										</AgencyCodedOther>
																									</xsl:for-each>
																								</Agency>
																							</xsl:for-each>
																							<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</xsl:for-each>
																				</ContactID>
																			</xsl:for-each>
																			<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																				<ContactName>
																					<xsl:value-of select="."/>
																				</ContactName>
																			</xsl:for-each>
																			<ContactFunction>
																				<xsl:for-each select="ContactFunction/ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<ContactFunctionCoded>
																						<xsl:value-of select="."/>
																					</ContactFunctionCoded>
																				</xsl:for-each>
																			</ContactFunction>
																			<xsl:for-each select="ListOfContactNumber">
																				<ListOfContactNumber>
																					<xsl:for-each select="ContactNumber">
																						<ContactNumber>
																							<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactNumberValue>
																									<xsl:value-of select="."/>
																								</ContactNumberValue>
																							</xsl:for-each>
																							<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactNumberTypeCoded>
																									<xsl:value-of select="."/>
																								</ContactNumberTypeCoded>
																							</xsl:for-each>
																						</ContactNumber>
																					</xsl:for-each>
																				</ListOfContactNumber>
																			</xsl:for-each>
																		</Contact>
																	</xsl:for-each>
																</OrderContact>
															</xsl:for-each>
															<xsl:for-each select="OtherContacts">
																<OtherContacts>
																	<xsl:for-each select="ListOfContact">
																		<ListOfContact>
																			<xsl:for-each select="Contact">
																				<Contact>
																					<xsl:for-each select="ContactID">
																						<ContactID>
																							<xsl:for-each select="Identifier">
																								<Identifier>
																									<xsl:for-each select="Agency">
																										<Agency>
																											<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCoded>
																													<xsl:value-of select="."/>
																												</AgencyCoded>
																											</xsl:for-each>
																											<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCodedOther>
																													<xsl:value-of select="."/>
																												</AgencyCodedOther>
																											</xsl:for-each>
																										</Agency>
																									</xsl:for-each>
																									<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																										<Ident>
																											<xsl:value-of select="."/>
																										</Ident>
																									</xsl:for-each>
																								</Identifier>
																							</xsl:for-each>
																						</ContactID>
																					</xsl:for-each>
																					<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																						<ContactName>
																							<xsl:value-of select="."/>
																						</ContactName>
																					</xsl:for-each>
																					<xsl:for-each select="ContactFunction">
																						<ContactFunction>
																							<xsl:for-each select="ContactFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ContactFunctionCoded>
																									<xsl:value-of select="."/>
																								</ContactFunctionCoded>
																							</xsl:for-each>
																						</ContactFunction>
																					</xsl:for-each>
																					<xsl:for-each select="ListOfContactNumber">
																						<ListOfContactNumber>
																							<xsl:for-each select="ContactNumber">
																								<ContactNumber>
																									<xsl:for-each select="ContactNumberValue/EDXBABLE/ORIGINATOR/VALUE">
																										<ContactNumberValue>
																											<xsl:value-of select="."/>
																										</ContactNumberValue>
																									</xsl:for-each>
																									<xsl:for-each select="ContactNumberTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<ContactNumberTypeCoded>
																											<xsl:value-of select="."/>
																										</ContactNumberTypeCoded>
																									</xsl:for-each>
																								</ContactNumber>
																							</xsl:for-each>
																						</ListOfContactNumber>
																					</xsl:for-each>
																				</Contact>
																			</xsl:for-each>
																		</ListOfContact>
																	</xsl:for-each>
																</OtherContacts>
															</xsl:for-each>
															<xsl:for-each select="CorrespondenceLanguage">
																<CorrespondenceLanguage>
																	<xsl:for-each select="Language">
																		<Language>
																			<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<LanguageCoded>
																					<xsl:value-of select="."/>
																				</LanguageCoded>
																			</xsl:for-each>
																		</Language>
																	</xsl:for-each>
																</CorrespondenceLanguage>
															</xsl:for-each>
														</Party>
													</xsl:for-each>
												</RemitToParty>
											</xsl:for-each>
										</OrderParty>
									</xsl:for-each>
									<ListOfTransport>
										<Transport>
											<TransportID>0</TransportID>
											<TransportMode>
												<TransportModeCoded>Other</TransportModeCoded>
												<TransportModeCodedOther>SEA FREIGHT</TransportModeCodedOther>
											</TransportMode>
											<CarrierID>
												<Identifier>
													<Agency>
														<AgencyCoded>AssignedBySellerOrSellersAgent</AgencyCoded>
													</Agency>
													<Ident></Ident>
												</Identifier>
											</CarrierID>
											<ShippingInstructions></ShippingInstructions>
											<TransitDirection>
												<TransitDirectionCoded>SellerToBuyer</TransitDirectionCoded>
											</TransitDirection>
										</Transport>
										<Transport>
											<TransportID>1</TransportID>
											<TransportMode>
												<TransportModeCoded>Other</TransportModeCoded>
												<TransportModeCodedOther></TransportModeCodedOther>
											</TransportMode>
											<CarrierID>
												<Identifier>
													<Agency>
														<AgencyCoded>AssignedBySellerOrSellersAgent</AgencyCoded>
													</Agency>
													<Ident></Ident>
												</Identifier>
											</CarrierID>
											<ShippingInstructions></ShippingInstructions>
											<TransitDirection>
												<TransitDirectionCoded>Other</TransitDirectionCoded>
											</TransitDirection>
										</Transport>
										<Transport>
											<TransportID>2</TransportID>
											<TransportMode>
												<TransportModeCoded>Other</TransportModeCoded>
												<TransportModeCodedOther></TransportModeCodedOther>
											</TransportMode>
											<CarrierID>
												<Identifier>
													<Agency>
														<AgencyCoded>AssignedBySellerOrSellersAgent</AgencyCoded>
													</Agency>
													<Ident></Ident>
												</Identifier>
											</CarrierID>
											<ShippingInstructions></ShippingInstructions>
											<TransitDirection>
												<TransitDirectionCoded>Other</TransitDirectionCoded>
											</TransitDirection>
										</Transport>
									</ListOfTransport>
									<xsl:for-each select="OrderHeaderNote/EDXBABLE/ORIGINATOR/VALUE">
										<OrderHeaderNote>
											<xsl:value-of select="."/>
										</OrderHeaderNote>
									</xsl:for-each>
									<xsl:for-each select="ListOfStructuredNote">
										<ListOfStructuredNote>
											<xsl:for-each select="StructuredNote">
												<StructuredNote>
													<xsl:for-each select="GeneralNote/EDXBABLE/ORIGINATOR/VALUE">
														<GeneralNote>
															<xsl:value-of select="."/>
														</GeneralNote>
													</xsl:for-each>
													<xsl:for-each select="NoteID/EDXBABLE/ORIGINATOR/VALUE">
														<NoteID>
															<xsl:value-of select="."/>
														</NoteID>
													</xsl:for-each>
													<xsl:for-each select="Agency">
														<Agency>
															<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																<AgencyCoded>
																	<xsl:value-of select="."/>
																</AgencyCoded>
															</xsl:for-each>
															<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																<AgencyCodedOther>
																	<xsl:value-of select="."/>
																</AgencyCodedOther>
															</xsl:for-each>
														</Agency>
													</xsl:for-each>
												</StructuredNote>
											</xsl:for-each>
										</ListOfStructuredNote>
									</xsl:for-each>
									<OrderHeaderAttachments>
										<ListOfAttachment>
											<Attachment>
												<AttachmentPurpose>xcbl/30</AttachmentPurpose>
												<FileName>xcbl/30</FileName>
												<ReplacementFile>false</ReplacementFile>
												<AttachmentLocation>urn:x-commerceone:package:com:commerceone:xcbl/30</AttachmentLocation>
											</Attachment>
										</ListOfAttachment>
									</OrderHeaderAttachments>
								</OrderHeader>
							</xsl:for-each>
						</OrderHeaderChanges>
					</xsl:for-each>
					<xsl:for-each select="TempOrderResponseHeaderNote/EDXBABLE/DESTINATION/RESOLVEDVALUE">
						<OrderResponseHeaderNote>
							<xsl:value-of select="."/>
						</OrderResponseHeaderNote>
					</xsl:for-each>
				</OrderResponseHeader>
			</xsl:for-each>
			<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderDetail">
				<OrderResponseDetail>
					<xsl:for-each select="ListOfItemDetail">
						<ListOfOrderResponseItemDetail>
							<xsl:for-each select="ItemDetail">
								<OrderResponseItemDetail>
									<xsl:for-each select="TempItemCoded/EDXBABLE/ORIGINATOR/VALUE">
										<ItemDetailResponseCoded>
											<xsl:value-of select="."/>
										</ItemDetailResponseCoded>
									</xsl:for-each>
									<OriginalItemDetail>
										<ItemDetail>
											<xsl:for-each select="BaseItemDetail">
												<BaseItemDetail>
													<xsl:for-each select="LineItemNum">
														<LineItemNum>
															<xsl:for-each select="BuyerLineItemNum/EDXBABLE/ORIGINATOR/VALUE">
																<BuyerLineItemNum>
																	<xsl:value-of select="."/>
																</BuyerLineItemNum>
															</xsl:for-each>
															<xsl:for-each select="BuyerLineItemNum/EDXBABLE/ORIGINATOR/VALUE">
																<SellerLineItemNum>
																	<xsl:value-of select="."/>
																</SellerLineItemNum>
															</xsl:for-each>
														</LineItemNum>
													</xsl:for-each>
													<xsl:for-each select="LineItemType">
														<LineItemType>
															<xsl:for-each select="LineItemTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																<LineItemTypeCoded>
																	<xsl:value-of select="."/>
																</LineItemTypeCoded>
															</xsl:for-each>
														</LineItemType>
													</xsl:for-each>
													<xsl:for-each select="ParentItemNumber">
														<ParentItemNumber>
															<xsl:for-each select="LineItemNumberReference/EDXBABLE/ORIGINATOR/VALUE">
																<LineItemNumberReference>
																	<xsl:value-of select="."/>
																</LineItemNumberReference>
															</xsl:for-each>
														</ParentItemNumber>
													</xsl:for-each>
													<xsl:for-each select="ItemIdentifiers">
														<ItemIdentifiers>
															<xsl:for-each select="PartNumbers">
																<PartNumbers>
																	<xsl:for-each select="SellerPartNumber">
																		<SellerPartNumber>
																			<xsl:for-each select="PartNum">
																				<PartNum>
																					<xsl:for-each select="PartID/EDXBABLE/ORIGINATOR/VALUE">
																						<PartID>
																							<xsl:value-of select="."/>
																						</PartID>
																					</xsl:for-each>
																					<xsl:for-each select="PartIDExt/EDXBABLE/ORIGINATOR/VALUE">
																						<PartIDExt>
																							<xsl:value-of select="."/>
																						</PartIDExt>
																					</xsl:for-each>
																				</PartNum>
																			</xsl:for-each>
																		</SellerPartNumber>
																	</xsl:for-each>
																	<xsl:for-each select="BuyerPartNumber">
																		<BuyerPartNumber>
																			<xsl:for-each select="PartNum">
																				<PartNum>
																					<xsl:for-each select="PartID/EDXBABLE/ORIGINATOR/VALUE">
																						<PartID>
																							<xsl:value-of select="."/>
																						</PartID>
																					</xsl:for-each>
																					<xsl:for-each select="PartIDExt/EDXBABLE/ORIGINATOR/VALUE">
																						<PartIDExt>
																							<xsl:value-of select="."/>
																						</PartIDExt>
																					</xsl:for-each>
																				</PartNum>
																			</xsl:for-each>
																		</BuyerPartNumber>
																	</xsl:for-each>
																	<xsl:for-each select="ManufacturerPartNumber">
																		<ManufacturerPartNumber>
																			<xsl:for-each select="PartID/EDXBABLE/ORIGINATOR/VALUE">
																				<PartID>
																					<xsl:value-of select="."/>
																				</PartID>
																			</xsl:for-each>
																			<xsl:for-each select="PartIDExt/EDXBABLE/ORIGINATOR/VALUE">
																				<PartIDExt>
																					<xsl:value-of select="."/>
																				</PartIDExt>
																			</xsl:for-each>
																			<xsl:for-each select="ManufacturerID">
																				<ManufacturerID>
																					<xsl:for-each select="Identifier">
																						<Identifier>
																							<xsl:for-each select="Agency">
																								<Agency>
																									<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCoded>
																											<xsl:value-of select="."/>
																										</AgencyCoded>
																									</xsl:for-each>
																									<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCodedOther>
																											<xsl:value-of select="."/>
																										</AgencyCodedOther>
																									</xsl:for-each>
																								</Agency>
																							</xsl:for-each>
																							<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</xsl:for-each>
																				</ManufacturerID>
																			</xsl:for-each>
																		</ManufacturerPartNumber>
																	</xsl:for-each>
																	<xsl:for-each select="StandardPartNumber">
																		<StandardPartNumber>
																			<xsl:for-each select="ProductIdentifierCoded">
																				<ProductIdentifierCoded>
																					<xsl:for-each select="ProductIdentifierQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<ProductIdentifierQualifierCoded>
																							<xsl:value-of select="."/>
																						</ProductIdentifierQualifierCoded>
																					</xsl:for-each>
																					<xsl:for-each select="ProductIdentifierQualifierCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<ProductIdentifierQualifierCodedOther>
																							<xsl:value-of select="."/>
																						</ProductIdentifierQualifierCodedOther>
																					</xsl:for-each>
																					<xsl:for-each select="ProductIdentifier/EDXBABLE/ORIGINATOR/VALUE">
																						<ProductIdentifier>
																							<xsl:value-of select="."/>
																						</ProductIdentifier>
																					</xsl:for-each>
																					<xsl:for-each select="ProductIdentifierExt/EDXBABLE/ORIGINATOR/VALUE">
																						<ProductIdentifierExt>
																							<xsl:value-of select="."/>
																						</ProductIdentifierExt>
																					</xsl:for-each>
																				</ProductIdentifierCoded>
																			</xsl:for-each>
																		</StandardPartNumber>
																	</xsl:for-each>
																	<xsl:for-each select="SubstitutePartNumbers">
																		<SubstitutePartNumbers>
																			<xsl:for-each select="ListOfProductIdentifierCoded">
																				<ListOfProductIdentifierCoded>
																					<xsl:for-each select="ProductIdentifierCoded">
																						<ProductIdentifierCoded>
																							<xsl:for-each select="ProductIdentifierQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ProductIdentifierQualifierCoded>
																									<xsl:value-of select="."/>
																								</ProductIdentifierQualifierCoded>
																							</xsl:for-each>
																							<xsl:for-each select="ProductIdentifierQualifierCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																								<ProductIdentifierQualifierCodedOther>
																									<xsl:value-of select="."/>
																								</ProductIdentifierQualifierCodedOther>
																							</xsl:for-each>
																							<xsl:for-each select="ProductIdentifier/EDXBABLE/ORIGINATOR/VALUE">
																								<ProductIdentifier>
																									<xsl:value-of select="."/>
																								</ProductIdentifier>
																							</xsl:for-each>
																							<xsl:for-each select="ProductIdentifierExt/EDXBABLE/ORIGINATOR/VALUE">
																								<ProductIdentifierExt>
																									<xsl:value-of select="."/>
																								</ProductIdentifierExt>
																							</xsl:for-each>
																						</ProductIdentifierCoded>
																					</xsl:for-each>
																				</ListOfProductIdentifierCoded>
																			</xsl:for-each>
																		</SubstitutePartNumbers>
																	</xsl:for-each>
																	<xsl:for-each select="OtherItemIdentifiers">
																		<OtherItemIdentifiers>
																			<xsl:for-each select="ListOfProductIdentifierCoded">
																				<ListOfProductIdentifierCoded>
																					<xsl:for-each select="ProductIdentifierCoded">
																						<ProductIdentifierCoded>
																							<xsl:for-each select="ProductIdentifierQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ProductIdentifierQualifierCoded>
																									<xsl:value-of select="."/>
																								</ProductIdentifierQualifierCoded>
																							</xsl:for-each>
																							<xsl:for-each select="ProductIdentifierQualifierCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																								<ProductIdentifierQualifierCodedOther>
																									<xsl:value-of select="."/>
																								</ProductIdentifierQualifierCodedOther>
																							</xsl:for-each>
																							<xsl:for-each select="ProductIdentifier/EDXBABLE/ORIGINATOR/VALUE">
																								<ProductIdentifier>
																									<xsl:value-of select="."/>
																								</ProductIdentifier>
																							</xsl:for-each>
																							<xsl:for-each select="ProductIdentifierExt/EDXBABLE/ORIGINATOR/VALUE">
																								<ProductIdentifierExt>
																									<xsl:value-of select="."/>
																								</ProductIdentifierExt>
																							</xsl:for-each>
																						</ProductIdentifierCoded>
																					</xsl:for-each>
																				</ListOfProductIdentifierCoded>
																			</xsl:for-each>
																		</OtherItemIdentifiers>
																	</xsl:for-each>
																</PartNumbers>
															</xsl:for-each>
															<xsl:for-each select="ItemDescription/EDXBABLE/ORIGINATOR/VALUE">
																<ItemDescription>
																	<xsl:value-of select="."/>
																</ItemDescription>
															</xsl:for-each>
															<xsl:for-each select="ListOfItemCharacteristic">
																<ListOfItemCharacteristic>
																	<xsl:for-each select="ItemCharacteristic">
																		<ItemCharacteristic>
																			<xsl:for-each select="ItemCharacteristicValue/EDXBABLE/ORIGINATOR/VALUE">
																				<ItemCharacteristicValue>
																					<xsl:value-of select="."/>
																				</ItemCharacteristicValue>
																			</xsl:for-each>
																			<xsl:for-each select="UnitOfMeasurement">
																				<UnitOfMeasurement>
																					<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<UOMCoded>
																							<xsl:value-of select="."/>
																						</UOMCoded>
																					</xsl:for-each>
																				</UnitOfMeasurement>
																			</xsl:for-each>
																		</ItemCharacteristic>
																	</xsl:for-each>
																</ListOfItemCharacteristic>
															</xsl:for-each>
															<xsl:for-each select="CommodityCode">
																<CommodityCode>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</CommodityCode>
															</xsl:for-each>
														</ItemIdentifiers>
													</xsl:for-each>
													<xsl:for-each select="ListOfDimension">
														<ListOfDimension>
															<xsl:for-each select="Dimension">
																<Dimension>
																	<xsl:for-each select="Measurement">
																		<Measurement>
																			<xsl:for-each select="MeasurementValue/EDXBABLE/ORIGINATOR/VALUE">
																				<MeasurementValue>
																					<xsl:value-of select="."/>
																				</MeasurementValue>
																			</xsl:for-each>
																			<xsl:for-each select="MeasurementRange">
																				<MeasurementRange>
																					<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																						<MinimumValue>
																							<xsl:value-of select="."/>
																						</MinimumValue>
																					</xsl:for-each>
																					<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																						<MaximumValue>
																							<xsl:value-of select="."/>
																						</MaximumValue>
																					</xsl:for-each>
																				</MeasurementRange>
																			</xsl:for-each>
																			<xsl:for-each select="UnitOfMeasurement">
																				<UnitOfMeasurement>
																					<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<UOMCoded>
																							<xsl:value-of select="."/>
																						</UOMCoded>
																					</xsl:for-each>
																				</UnitOfMeasurement>
																			</xsl:for-each>
																		</Measurement>
																	</xsl:for-each>
																	<xsl:for-each select="DimensionCoded/EDXBABLE/ORIGINATOR/VALUE">
																		<DimensionCoded>
																			<xsl:value-of select="."/>
																		</DimensionCoded>
																	</xsl:for-each>
																</Dimension>
															</xsl:for-each>
														</ListOfDimension>
													</xsl:for-each>
													<xsl:for-each select="TotalQuantity">
														<TotalQuantity>
															<xsl:for-each select="Quantity">
																<Quantity>
																	<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																		<QuantityValue>
																			<xsl:value-of select="."/>
																		</QuantityValue>
																	</xsl:for-each>
																	<xsl:for-each select="QuantityRange">
																		<QuantityRange>
																			<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																				<MinimumValue>
																					<xsl:value-of select="."/>
																				</MinimumValue>
																			</xsl:for-each>
																			<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																				<MaximumValue>
																					<xsl:value-of select="."/>
																				</MaximumValue>
																			</xsl:for-each>
																		</QuantityRange>
																	</xsl:for-each>
																	<xsl:for-each select="UnitOfMeasurement">
																		<UnitOfMeasurement>
																			<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<UOMCoded>
																					<xsl:value-of select="."/>
																				</UOMCoded>
																			</xsl:for-each>
																			<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<UOMCodedOther>
																					<xsl:value-of select="."/>
																				</UOMCodedOther>
																			</xsl:for-each>
																		</UnitOfMeasurement>
																	</xsl:for-each>
																</Quantity>
															</xsl:for-each>
														</TotalQuantity>
													</xsl:for-each>
													<xsl:for-each select="MaxBackOrderQuantity">
														<MaxBackOrderQuantity>
															<xsl:for-each select="Quantity">
																<Quantity>
																	<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																		<QuantityValue>
																			<xsl:value-of select="."/>
																		</QuantityValue>
																	</xsl:for-each>
																	<xsl:for-each select="QuantityRange">
																		<QuantityRange>
																			<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																				<MinimumValue>
																					<xsl:value-of select="."/>
																				</MinimumValue>
																			</xsl:for-each>
																			<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																				<MaximumValue>
																					<xsl:value-of select="."/>
																				</MaximumValue>
																			</xsl:for-each>
																		</QuantityRange>
																	</xsl:for-each>
																	<xsl:for-each select="UnitOfMeasurement">
																		<UnitOfMeasurement>
																			<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<UOMCoded>
																					<xsl:value-of select="."/>
																				</UOMCoded>
																			</xsl:for-each>
																		</UnitOfMeasurement>
																	</xsl:for-each>
																</Quantity>
															</xsl:for-each>
														</MaxBackOrderQuantity>
													</xsl:for-each>
													<xsl:for-each select="OffCatalogFlag/EDXBABLE/ORIGINATOR/VALUE">
														<OffCatalogFlag>false
															<xsl:value-of select="."/>
														</OffCatalogFlag>
													</xsl:for-each>
													<xsl:for-each select="ListOfItemReferences">
														<ListOfItemReferences>
															<xsl:for-each select="ListOfReferenceCoded">
																<ListOfReferenceCoded>
																	<xsl:for-each select="ReferenceCoded">
																		<ReferenceCoded>
																			<xsl:for-each select="ReferenceTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<ReferenceTypeCoded>
																					<xsl:value-of select="."/>
																				</ReferenceTypeCoded>
																			</xsl:for-each>
																			<xsl:for-each select="PrimaryReference">
																				<PrimaryReference>
																					<xsl:for-each select="Reference">
																						<Reference>
																							<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																								<RefNum>
																									<xsl:value-of select="."/>
																								</RefNum>
																							</xsl:for-each>
																						</Reference>
																					</xsl:for-each>
																				</PrimaryReference>
																			</xsl:for-each>
																			<xsl:for-each select="SupportingReference">
																				<SupportingReference>
																					<xsl:for-each select="Reference">
																						<Reference>
																							<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																								<RefNum>
																									<xsl:value-of select="."/>
																								</RefNum>
																							</xsl:for-each>
																							<xsl:for-each select="RefDate/EDXBABLE/ORIGINATOR/VALUE">
																								<RefDate>
																									<xsl:value-of select="."/>
																								</RefDate>
																							</xsl:for-each>
																						</Reference>
																					</xsl:for-each>
																				</SupportingReference>
																			</xsl:for-each>
																		</ReferenceCoded>
																	</xsl:for-each>
																</ListOfReferenceCoded>
															</xsl:for-each>
														</ListOfItemReferences>
													</xsl:for-each>
												</BaseItemDetail>
											</xsl:for-each>
											<xsl:for-each select="PricingDetail">
												<PricingDetail>
													<xsl:for-each select="ListOfPrice">
														<ListOfPrice>
															<xsl:for-each select="Price">
																<Price>
																	<xsl:for-each select="UnitPrice">
																		<UnitPrice>
																			<xsl:for-each select="UnitPriceValue/EDXBABLE/ORIGINATOR/VALUE">
																				<UnitPriceValue>
																					<xsl:value-of select="."/>
																				</UnitPriceValue>
																			</xsl:for-each>
																			<xsl:for-each select="Currency">
																				<Currency>
																					<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<CurrencyCoded>
																							<xsl:value-of select="."/>
																						</CurrencyCoded>
																					</xsl:for-each>
																				</Currency>
																			</xsl:for-each>
																			<xsl:for-each select="UnitOfMeasurement">
																				<UnitOfMeasurement>
																					<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<UOMCoded>
																							<xsl:value-of select="."/>
																						</UOMCoded>
																					</xsl:for-each>
																					<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<UOMCodedOther>
																							<xsl:value-of select="."/>
																						</UOMCodedOther>
																					</xsl:for-each>
																				</UnitOfMeasurement>
																			</xsl:for-each>
																		</UnitPrice>
																	</xsl:for-each>
																	<xsl:for-each select="PriceBasisQuantity">
																		<PriceBasisQuantity>
																			<xsl:for-each select="Quantity">
																				<Quantity>
																					<xsl:for-each select="QuantityValue">
																						<QuantityValue/>
																					</xsl:for-each>
																					<xsl:for-each select="QuantityRange">
																						<QuantityRange>
																							<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																								<MinimumValue>
																									<xsl:value-of select="."/>
																								</MinimumValue>
																							</xsl:for-each>
																							<xsl:for-each select="MaximumValue">
																								<MaximumValue/>
																							</xsl:for-each>
																						</QuantityRange>
																					</xsl:for-each>
																					<xsl:for-each select="UnitOfMeasurement">
																						<UnitOfMeasurement>
																							<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<UOMCoded>
																									<xsl:value-of select="."/>
																								</UOMCoded>
																							</xsl:for-each>
																						</UnitOfMeasurement>
																					</xsl:for-each>
																				</Quantity>
																			</xsl:for-each>
																		</PriceBasisQuantity>
																	</xsl:for-each>
																	<xsl:for-each select="ValidityDates">
																		<ValidityDates>
																			<xsl:for-each select="StartDate/EDXBABLE/ORIGINATOR/VALUE">
																				<StartDate>
																					<xsl:value-of select="."/>
																				</StartDate>
																			</xsl:for-each>
																			<xsl:for-each select="EndDate/EDXBABLE/ORIGINATOR/VALUE">
																				<EndDate>
																					<xsl:value-of select="."/>
																				</EndDate>
																			</xsl:for-each>
																		</ValidityDates>
																	</xsl:for-each>
																</Price>
															</xsl:for-each>
														</ListOfPrice>
													</xsl:for-each>
													<xsl:for-each select="Tax">
														<Tax>
															<xsl:for-each select="TaxFunctionQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																<TaxFunctionQualifierCoded>
																	<xsl:value-of select="."/>
																</TaxFunctionQualifierCoded>
															</xsl:for-each>
															<xsl:for-each select="TaxCategoryCoded/EDXBABLE/ORIGINATOR/VALUE">
																<TaxCategoryCoded>
																	<xsl:value-of select="."/>
																</TaxCategoryCoded>
															</xsl:for-each>
															<xsl:for-each select="TaxCategoryCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																<TaxCategoryCodedOther>
																	<xsl:value-of select="."/>
																</TaxCategoryCodedOther>
															</xsl:for-each>
															<xsl:for-each select="TaxTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																<TaxTypeCoded>
																	<xsl:value-of select="."/>
																</TaxTypeCoded>
															</xsl:for-each>
															<xsl:for-each select="TaxTypeCodedOther">
																<TaxTypeCodedOther>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</TaxTypeCodedOther>
															</xsl:for-each>
															<xsl:for-each select="TaxPercent/EDXBABLE/ORIGINATOR/VALUE">
																<TaxPercent>
																	<xsl:value-of select="."/>
																</TaxPercent>
															</xsl:for-each>
															<xsl:for-each select="TaxAmount/EDXBABLE/ORIGINATOR/VALUE">
																<TaxAmount>
																	<xsl:value-of select="."/>
																</TaxAmount>
															</xsl:for-each>
															<xsl:for-each select="TaxableAmount/EDXBABLE/ORIGINATOR/VALUE">
																<TaxableAmount>
																	<xsl:value-of select="."/>
																</TaxableAmount>
															</xsl:for-each>
															<xsl:for-each select="TaxLocation">
																<TaxLocation>
																	<xsl:for-each select="Location">
																		<Location>
																			<xsl:for-each select="LocationIdentifier">
																				<LocationIdentifier>
																					<xsl:for-each select="LocID">
																						<LocID>
																							<xsl:for-each select="Identifier">
																								<Identifier>
																									<xsl:for-each select="Agency">
																										<Agency>
																											<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCoded>
																													<xsl:value-of select="."/>
																												</AgencyCoded>
																											</xsl:for-each>
																											<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCodedOther>
																													<xsl:value-of select="."/>
																												</AgencyCodedOther>
																											</xsl:for-each>
																										</Agency>
																									</xsl:for-each>
																									<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																										<Ident>
																											<xsl:value-of select="."/>
																										</Ident>
																									</xsl:for-each>
																								</Identifier>
																							</xsl:for-each>
																						</LocID>
																					</xsl:for-each>
																				</LocationIdentifier>
																			</xsl:for-each>
																			<ExternalAddressID/>
																			<NameAddress>
																				<Name1/>
																			</NameAddress>
																		</Location>
																	</xsl:for-each>
																</TaxLocation>
															</xsl:for-each>
														</Tax>
													</xsl:for-each>
													<xsl:for-each select="ItemAllowancesOrCharges">
														<ItemAllowancesOrCharges>
															<xsl:for-each select="ListOfAllowOrCharge">
																<ListOfAllowOrCharge>
																	<xsl:for-each select="AllowOrCharge">
																		<AllowOrCharge>
																			<xsl:for-each select="IndicatorCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<IndicatorCoded>
																					<xsl:value-of select="."/>
																				</IndicatorCoded>
																			</xsl:for-each>
																			<xsl:for-each select="BasisCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<BasisCoded>
																					<xsl:value-of select="."/>
																				</BasisCoded>
																			</xsl:for-each>
																			<xsl:for-each select="MethodOfHandlingCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<MethodOfHandlingCoded>
																					<xsl:value-of select="."/>
																				</MethodOfHandlingCoded>
																			</xsl:for-each>
																			<xsl:for-each select="MethodOfHandlingCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<MethodOfHandlingCodedOther>
																					<xsl:value-of select="."/>
																				</MethodOfHandlingCodedOther>
																			</xsl:for-each>
																			<xsl:for-each select="AllowanceOrChargeDescription">
																				<AllowanceOrChargeDescription>
																					<xsl:for-each select="AllowOrChgDesc">
																						<AllowOrChgDesc>
																							<xsl:for-each select="ListOfDescription">
																								<ListOfDescription>
																									<xsl:for-each select="Description">
																										<Description>
																											<xsl:for-each select="DescriptionText/EDXBABLE/ORIGINATOR/VALUE">
																												<DescriptionText>
																													<xsl:value-of select="."/>
																												</DescriptionText>
																											</xsl:for-each>
																											<xsl:for-each select="Language">
																												<Language>
																													<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<LanguageCoded>
																															<xsl:value-of select="."/>
																														</LanguageCoded>
																													</xsl:for-each>
																												</Language>
																											</xsl:for-each>
																										</Description>
																									</xsl:for-each>
																								</ListOfDescription>
																							</xsl:for-each>
																							<xsl:for-each select="ServiceCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<ServiceCoded>
																									<xsl:value-of select="."/>
																								</ServiceCoded>
																							</xsl:for-each>
																						</AllowOrChgDesc>
																					</xsl:for-each>
																				</AllowanceOrChargeDescription>
																			</xsl:for-each>
																			<xsl:for-each select="BasisQuantityRange">
																				<BasisQuantityRange>
																					<xsl:for-each select="Quantity">
																						<Quantity>
																							<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																								<QuantityValue>
																									<xsl:value-of select="."/>
																								</QuantityValue>
																							</xsl:for-each>
																							<xsl:for-each select="QuantityRange">
																								<QuantityRange>
																									<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																										<MinimumValue>
																											<xsl:value-of select="."/>
																										</MinimumValue>
																									</xsl:for-each>
																									<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																										<MaximumValue>
																											<xsl:value-of select="."/>
																										</MaximumValue>
																									</xsl:for-each>
																								</QuantityRange>
																							</xsl:for-each>
																							<xsl:for-each select="UnitOfMeasurement">
																								<UnitOfMeasurement>
																									<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<UOMCoded>
																											<xsl:value-of select="."/>
																										</UOMCoded>
																									</xsl:for-each>
																									<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<UOMCodedOther>
																											<xsl:value-of select="."/>
																										</UOMCodedOther>
																									</xsl:for-each>
																								</UnitOfMeasurement>
																							</xsl:for-each>
																						</Quantity>
																					</xsl:for-each>
																				</BasisQuantityRange>
																			</xsl:for-each>
																			<xsl:for-each select="BasisMonetaryRange">
																				<BasisMonetaryRange>
																					<MonetaryRange>
																						<xsl:for-each select="MonetaryRange/MinimumMonetaryValue/EDXBABLE/ORIGINATOR/VALUE">
																							<MinimumMonetaryValue>
																								<xsl:value-of select="."/>
																							</MinimumMonetaryValue>
																						</xsl:for-each>
																						<xsl:for-each select="MonetaryRange/MaximumMonetaryValue/EDXBABLE/ORIGINATOR/VALUE">
																							<MaximumMonetaryValue>
																								<xsl:value-of select="."/>
																							</MaximumMonetaryValue>
																						</xsl:for-each>
																						<xsl:for-each select="MonetaryRange/Currency">
																							<Currency>
																								<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																									<CurrencyCoded>
																										<xsl:value-of select="."/>
																									</CurrencyCoded>
																								</xsl:for-each>
																								<xsl:for-each select="CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																									<CurrencyCodedOther>
																										<xsl:value-of select="."/>
																									</CurrencyCodedOther>
																								</xsl:for-each>
																							</Currency>
																						</xsl:for-each>
																					</MonetaryRange>
																					<xsl:for-each select="MonetaryLimit">
																						<MonetaryLimit>
																							<xsl:for-each select="MonetaryLimitValue/EDXBABLE/ORIGINATOR/VALUE">
																								<MonetaryLimitValue>
																									<xsl:value-of select="."/>
																								</MonetaryLimitValue>
																							</xsl:for-each>
																							<xsl:for-each select="Currency">
																								<Currency>
																									<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<CurrencyCoded>
																											<xsl:value-of select="."/>
																										</CurrencyCoded>
																									</xsl:for-each>
																									<xsl:for-each select="CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<CurrencyCodedOther>
																											<xsl:value-of select="."/>
																										</CurrencyCodedOther>
																									</xsl:for-each>
																								</Currency>
																							</xsl:for-each>
																						</MonetaryLimit>
																					</xsl:for-each>
																				</BasisMonetaryRange>
																			</xsl:for-each>
																			<xsl:for-each select="TypeOfAllowanceOrCharge">
																				<TypeOfAllowanceOrCharge>
																					<xsl:for-each select="QuantityAllowanceOrCharge">
																						<QuantityAllowanceOrCharge>
																							<xsl:for-each select="Quantity">
																								<Quantity>
																									<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																										<QuantityValue>
																											<xsl:value-of select="."/>
																										</QuantityValue>
																									</xsl:for-each>
																									<xsl:for-each select="QuantityRange">
																										<QuantityRange>
																											<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																												<MinimumValue>
																													<xsl:value-of select="."/>
																												</MinimumValue>
																											</xsl:for-each>
																											<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																												<MaximumValue>
																													<xsl:value-of select="."/>
																												</MaximumValue>
																											</xsl:for-each>
																										</QuantityRange>
																									</xsl:for-each>
																									<xsl:for-each select="UnitOfMeasurement">
																										<UnitOfMeasurement>
																											<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																												<UOMCoded>
																													<xsl:value-of select="."/>
																												</UOMCoded>
																											</xsl:for-each>
																											<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																												<UOMCodedOther>
																													<xsl:value-of select="."/>
																												</UOMCodedOther>
																											</xsl:for-each>
																										</UnitOfMeasurement>
																									</xsl:for-each>
																								</Quantity>
																							</xsl:for-each>
																							<xsl:for-each select="Rate">
																								<Rate>
																									<xsl:for-each select="RatePerUnit">
																										<RatePerUnit>
																											<UnitPrice>
																												<xsl:for-each select="UnitPrice/UnitPriceValue/EDXBABLE/ORIGINATOR/VALUE">
																													<UnitPriceValue>
																														<xsl:value-of select="."/>
																													</UnitPriceValue>
																												</xsl:for-each>
																												<xsl:for-each select="UnitPrice/Currency">
																													<Currency>
																														<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																															<CurrencyCoded>
																																<xsl:value-of select="."/>
																															</CurrencyCoded>
																														</xsl:for-each>
																													</Currency>
																												</xsl:for-each>
																											</UnitPrice>
																										</RatePerUnit>
																									</xsl:for-each>
																									<xsl:for-each select="UnitPriceBasis/EDXBABLE/ORIGINATOR/VALUE">
																										<UnitPriceBasis>
																											<xsl:value-of select="."/>
																										</UnitPriceBasis>
																									</xsl:for-each>
																									<xsl:for-each select="UnitOfMeasurement">
																										<UnitOfMeasurement>
																											<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																												<UOMCoded>
																													<xsl:value-of select="."/>
																												</UOMCoded>
																											</xsl:for-each>
																											<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																												<UOMCodedOther>
																													<xsl:value-of select="."/>
																												</UOMCodedOther>
																											</xsl:for-each>
																										</UnitOfMeasurement>
																									</xsl:for-each>
																								</Rate>
																							</xsl:for-each>
																							<xsl:for-each select="QuantityMonetaryValue">
																								<QuantityMonetaryValue>
																									<xsl:for-each select="MonetaryValue">
																										<MonetaryValue>
																											<xsl:for-each select="MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE">
																												<MonetaryAmount>
																													<xsl:value-of select="."/>
																												</MonetaryAmount>
																											</xsl:for-each>
																											<xsl:for-each select="Currency">
																												<Currency>
																													<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<CurrencyCoded>
																															<xsl:value-of select="."/>
																														</CurrencyCoded>
																													</xsl:for-each>
																												</Currency>
																											</xsl:for-each>
																											<xsl:for-each select="RateOfExchangeDetail">
																												<RateOfExchangeDetail>
																													<xsl:for-each select="ReferenceCurrency">
																														<ReferenceCurrency>
																															<xsl:for-each select="Currency">
																																<Currency>
																																	<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																																		<CurrencyCoded>
																																			<xsl:value-of select="."/>
																																		</CurrencyCoded>
																																	</xsl:for-each>
																																	<xsl:for-each select="CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																																		<CurrencyCodedOther>
																																			<xsl:value-of select="."/>
																																		</CurrencyCodedOther>
																																	</xsl:for-each>
																																</Currency>
																															</xsl:for-each>
																														</ReferenceCurrency>
																													</xsl:for-each>
																													<xsl:for-each select="TargetCurrency">
																														<TargetCurrency>
																															<xsl:for-each select="Currency">
																																<Currency>
																																	<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																																		<CurrencyCoded>
																																			<xsl:value-of select="."/>
																																		</CurrencyCoded>
																																	</xsl:for-each>
																																	<xsl:for-each select="CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																																		<CurrencyCodedOther>
																																			<xsl:value-of select="."/>
																																		</CurrencyCodedOther>
																																	</xsl:for-each>
																																</Currency>
																															</xsl:for-each>
																														</TargetCurrency>
																													</xsl:for-each>
																													<xsl:for-each select="RateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
																														<RateOfExchange>
																															<xsl:value-of select="."/>
																														</RateOfExchange>
																													</xsl:for-each>
																													<xsl:for-each select="InverseRateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
																														<InverseRateOfExchange>
																															<xsl:value-of select="."/>
																														</InverseRateOfExchange>
																													</xsl:for-each>
																													<xsl:for-each select="DateOfRateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
																														<DateOfRateOfExchange>
																															<xsl:value-of select="."/>
																														</DateOfRateOfExchange>
																													</xsl:for-each>
																													<xsl:for-each select="ListOfRateOfExchangeReference">
																														<ListOfRateOfExchangeReference>
																															<xsl:for-each select="ListOfReference">
																																<ListOfReference>
																																	<xsl:for-each select="Reference">
																																		<Reference>
																																			<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																																				<RefNum>
																																					<xsl:value-of select="."/>
																																				</RefNum>
																																			</xsl:for-each>
																																			<xsl:for-each select="RefDate/EDXBABLE/ORIGINATOR/VALUE">
																																				<RefDate>
																																					<xsl:value-of select="."/>
																																				</RefDate>
																																			</xsl:for-each>
																																		</Reference>
																																	</xsl:for-each>
																																</ListOfReference>
																															</xsl:for-each>
																														</ListOfRateOfExchangeReference>
																													</xsl:for-each>
																												</RateOfExchangeDetail>
																											</xsl:for-each>
																										</MonetaryValue>
																									</xsl:for-each>
																								</QuantityMonetaryValue>
																							</xsl:for-each>
																						</QuantityAllowanceOrCharge>
																					</xsl:for-each>
																					<xsl:for-each select="PercentageAllowanceOrCharge">
																						<PercentageAllowanceOrCharge>
																							<xsl:for-each select="PercentQualifier">
																								<PercentQualifier>
																									<xsl:for-each select="PercentQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<PercentQualifierCoded>
																											<xsl:value-of select="."/>
																										</PercentQualifierCoded>
																									</xsl:for-each>
																									<xsl:for-each select="PercentQualifierCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<PercentQualifierCodedOther>
																											<xsl:value-of select="."/>
																										</PercentQualifierCodedOther>
																									</xsl:for-each>
																								</PercentQualifier>
																							</xsl:for-each>
																							<xsl:for-each select="Percent/EDXBABLE/ORIGINATOR/VALUE">
																								<Percent>
																									<xsl:value-of select="."/>
																								</Percent>
																							</xsl:for-each>
																							<xsl:for-each select="PercentageMonetaryValue">
																								<PercentageMonetaryValue>
																									<xsl:for-each select="MonetaryValue">
																										<MonetaryValue>
																											<xsl:for-each select="MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE">
																												<MonetaryAmount>
																													<xsl:value-of select="."/>
																												</MonetaryAmount>
																											</xsl:for-each>
																											<xsl:for-each select="Currency">
																												<Currency>
																													<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<CurrencyCoded>
																															<xsl:value-of select="."/>
																														</CurrencyCoded>
																													</xsl:for-each>
																												</Currency>
																											</xsl:for-each>
																											<xsl:for-each select="RateOfExchangeDetail">
																												<RateOfExchangeDetail>
																													<xsl:for-each select="ReferenceCurrency">
																														<ReferenceCurrency>
																															<xsl:for-each select="Currency">
																																<Currency>
																																	<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																																		<CurrencyCoded>
																																			<xsl:value-of select="."/>
																																		</CurrencyCoded>
																																	</xsl:for-each>
																																	<xsl:for-each select="CurrencyCodedOther">
																																		<CurrencyCodedOther>
																																			<xsl:value-of select="."/>
																																		</CurrencyCodedOther>
																																	</xsl:for-each>
																																</Currency>
																															</xsl:for-each>
																														</ReferenceCurrency>
																													</xsl:for-each>
																													<xsl:for-each select="TargetCurrency">
																														<TargetCurrency>
																															<xsl:for-each select="Currency">
																																<Currency>
																																	<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																																		<CurrencyCoded>
																																			<xsl:value-of select="."/>
																																		</CurrencyCoded>
																																	</xsl:for-each>
																																	<xsl:for-each select="CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																																		<CurrencyCodedOther>
																																			<xsl:value-of select="."/>
																																		</CurrencyCodedOther>
																																	</xsl:for-each>
																																</Currency>
																															</xsl:for-each>
																														</TargetCurrency>
																													</xsl:for-each>
																													<xsl:for-each select="RateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
																														<RateOfExchange>
																															<xsl:value-of select="."/>
																														</RateOfExchange>
																													</xsl:for-each>
																													<xsl:for-each select="InverseRateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
																														<InverseRateOfExchange>
																															<xsl:value-of select="."/>
																														</InverseRateOfExchange>
																													</xsl:for-each>
																													<xsl:for-each select="DateOfRateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
																														<DateOfRateOfExchange>
																															<xsl:value-of select="."/>
																														</DateOfRateOfExchange>
																													</xsl:for-each>
																													<xsl:for-each select="ListOfRateOfExchangeReference">
																														<ListOfRateOfExchangeReference>
																															<xsl:for-each select="ListOfReference">
																																<ListOfReference>
																																	<xsl:for-each select="Reference">
																																		<Reference>
																																			<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																																				<RefNum>
																																					<xsl:value-of select="."/>
																																				</RefNum>
																																			</xsl:for-each>
																																			<xsl:for-each select="RefDate/EDXBABLE/ORIGINATOR/VALUE">
																																				<RefDate>
																																					<xsl:value-of select="."/>
																																				</RefDate>
																																			</xsl:for-each>
																																		</Reference>
																																	</xsl:for-each>
																																</ListOfReference>
																															</xsl:for-each>
																														</ListOfRateOfExchangeReference>
																													</xsl:for-each>
																												</RateOfExchangeDetail>
																											</xsl:for-each>
																										</MonetaryValue>
																									</xsl:for-each>
																								</PercentageMonetaryValue>
																							</xsl:for-each>
																						</PercentageAllowanceOrCharge>
																					</xsl:for-each>
																					<xsl:for-each select="MonetaryValue">
																						<MonetaryValue>
																							<xsl:for-each select="MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE">
																								<MonetaryAmount>
																									<xsl:value-of select="."/>
																								</MonetaryAmount>
																							</xsl:for-each>
																							<xsl:for-each select="Currency">
																								<Currency>
																									<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<CurrencyCoded>
																											<xsl:value-of select="."/>
																										</CurrencyCoded>
																									</xsl:for-each>
																									<xsl:for-each select="CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<CurrencyCodedOther>
																											<xsl:value-of select="."/>
																										</CurrencyCodedOther>
																									</xsl:for-each>
																								</Currency>
																							</xsl:for-each>
																							<xsl:for-each select="../../../../../TotalValue/MonetaryValue/RateOfExchangeDetail">
																								<RateOfExchangeDetail>
																									<xsl:for-each select="ReferenceCurrency">
																										<ReferenceCurrency>
																											<xsl:for-each select="Currency">
																												<Currency>
																													<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<CurrencyCoded>
																															<xsl:value-of select="."/>
																														</CurrencyCoded>
																													</xsl:for-each>
																													<xsl:for-each select="CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																														<CurrencyCodedOther>
																															<xsl:value-of select="."/>
																														</CurrencyCodedOther>
																													</xsl:for-each>
																												</Currency>
																											</xsl:for-each>
																										</ReferenceCurrency>
																									</xsl:for-each>
																									<xsl:for-each select="TargetCurrency">
																										<TargetCurrency>
																											<xsl:for-each select="Currency">
																												<Currency>
																													<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<CurrencyCoded>
																															<xsl:value-of select="."/>
																														</CurrencyCoded>
																													</xsl:for-each>
																													<xsl:for-each select="CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																														<CurrencyCodedOther>
																															<xsl:value-of select="."/>
																														</CurrencyCodedOther>
																													</xsl:for-each>
																												</Currency>
																											</xsl:for-each>
																										</TargetCurrency>
																									</xsl:for-each>
																									<xsl:for-each select="RateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
																										<RateOfExchange>
																											<xsl:value-of select="."/>
																										</RateOfExchange>
																									</xsl:for-each>
																									<xsl:for-each select="InverseRateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
																										<InverseRateOfExchange>
																											<xsl:value-of select="."/>
																										</InverseRateOfExchange>
																									</xsl:for-each>
																									<xsl:for-each select="DateOfRateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
																										<DateOfRateOfExchange>
																											<xsl:value-of select="."/>
																										</DateOfRateOfExchange>
																									</xsl:for-each>
																									<xsl:for-each select="ListOfRateOfExchangeReference">
																										<ListOfRateOfExchangeReference>
																											<xsl:for-each select="ListOfReference">
																												<ListOfReference>
																													<xsl:for-each select="Reference">
																														<Reference>
																															<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																																<RefNum>
																																	<xsl:value-of select="."/>
																																</RefNum>
																															</xsl:for-each>
																															<xsl:for-each select="RefDate/EDXBABLE/ORIGINATOR/VALUE">
																																<RefDate>
																																	<xsl:value-of select="."/>
																																</RefDate>
																															</xsl:for-each>
																														</Reference>
																													</xsl:for-each>
																												</ListOfReference>
																											</xsl:for-each>
																										</ListOfRateOfExchangeReference>
																									</xsl:for-each>
																								</RateOfExchangeDetail>
																							</xsl:for-each>
																						</MonetaryValue>
																					</xsl:for-each>
																				</TypeOfAllowanceOrCharge>
																			</xsl:for-each>
																			<xsl:for-each select="Tax">
																				<Tax>
																					<xsl:for-each select="TaxFunctionQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<TaxFunctionQualifierCoded>
																							<xsl:value-of select="."/>
																						</TaxFunctionQualifierCoded>
																					</xsl:for-each>
																					<xsl:for-each select="TaxFunctionQualifierCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<TaxFunctionQualifierCodedOther>
																							<xsl:value-of select="."/>
																						</TaxFunctionQualifierCodedOther>
																					</xsl:for-each>
																					<xsl:for-each select="TaxCategoryCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<TaxCategoryCoded>
																							<xsl:value-of select="."/>
																						</TaxCategoryCoded>
																					</xsl:for-each>
																					<xsl:for-each select="TaxTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<TaxTypeCoded>
																							<xsl:value-of select="."/>
																						</TaxTypeCoded>
																					</xsl:for-each>
																					<xsl:for-each select="TaxTypeCodedOther">
																						<TaxTypeCodedOther>
																							<xsl:for-each select="Identifier">
																								<Identifier>
																									<xsl:for-each select="Agency">
																										<Agency>
																											<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																												<AgencyCoded>
																													<xsl:value-of select="."/>
																												</AgencyCoded>
																											</xsl:for-each>
																										</Agency>
																									</xsl:for-each>
																									<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																										<Ident>
																											<xsl:value-of select="."/>
																										</Ident>
																									</xsl:for-each>
																								</Identifier>
																							</xsl:for-each>
																						</TaxTypeCodedOther>
																					</xsl:for-each>
																					<xsl:for-each select="TaxAmount/EDXBABLE/ORIGINATOR/VALUE">
																						<TaxAmount>
																							<xsl:value-of select="."/>
																						</TaxAmount>
																					</xsl:for-each>
																					<xsl:for-each select="TaxLocation">
																						<TaxLocation>
																							<xsl:for-each select="Location">
																								<Location>
																									<xsl:for-each select="LocationIdentifier">
																										<LocationIdentifier>
																											<xsl:for-each select="LocID">
																												<LocID>
																													<xsl:for-each select="Identifier">
																														<Identifier>
																															<xsl:for-each select="Agency">
																																<Agency>
																																	<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																																		<AgencyCoded>
																																			<xsl:value-of select="."/>
																																		</AgencyCoded>
																																	</xsl:for-each>
																																</Agency>
																															</xsl:for-each>
																															<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																																<Ident>
																																	<xsl:value-of select="."/>
																																</Ident>
																															</xsl:for-each>
																														</Identifier>
																													</xsl:for-each>
																												</LocID>
																											</xsl:for-each>
																										</LocationIdentifier>
																									</xsl:for-each>
																									<xsl:for-each select="ExternalAddressID/EDXBABLE/ORIGINATOR/VALUE">
																										<ExternalAddressID>
																											<xsl:value-of select="."/>
																										</ExternalAddressID>
																									</xsl:for-each>
																									<xsl:for-each select="NameAddress">
																										<NameAddress>
																											<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																												<Name1>
																													<xsl:value-of select="."/>
																												</Name1>
																											</xsl:for-each>
																											<xsl:for-each select="Identifier">
																												<Identifier>
																													<xsl:for-each select="Agency">
																														<Agency>
																															<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																																<AgencyCoded>
																																	<xsl:value-of select="."/>
																																</AgencyCoded>
																															</xsl:for-each>
																														</Agency>
																													</xsl:for-each>
																													<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																														<Ident>
																															<xsl:value-of select="."/>
																														</Ident>
																													</xsl:for-each>
																												</Identifier>
																											</xsl:for-each>
																											<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																												<POBox>
																													<xsl:value-of select="."/>
																												</POBox>
																											</xsl:for-each>
																											<xsl:for-each select="Region">
																												<Region>
																													<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<RegionCoded>
																															<xsl:value-of select="."/>
																														</RegionCoded>
																													</xsl:for-each>
																													<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																														<RegionCodedOther>
																															<xsl:value-of select="."/>
																														</RegionCodedOther>
																													</xsl:for-each>
																												</Region>
																											</xsl:for-each>
																											<xsl:for-each select="Country">
																												<Country>
																													<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<CountryCoded>
																															<xsl:value-of select="."/>
																														</CountryCoded>
																													</xsl:for-each>
																												</Country>
																											</xsl:for-each>
																											<xsl:for-each select="Timezone">
																												<Timezone>
																													<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<TimezoneCoded>
																															<xsl:value-of select="."/>
																														</TimezoneCoded>
																													</xsl:for-each>
																													<xsl:for-each select="TimezoneCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																														<TimezoneCodedOther>
																															<xsl:value-of select="."/>
																														</TimezoneCodedOther>
																													</xsl:for-each>
																												</Timezone>
																											</xsl:for-each>
																										</NameAddress>
																									</xsl:for-each>
																									<xsl:for-each select="GPSCooridinates">
																										<GPSCooridinates>
																											<xsl:for-each select="GPSSystem/EDXBABLE/ORIGINATOR/VALUE">
																												<GPSSystem>
																													<xsl:value-of select="."/>
																												</GPSSystem>
																											</xsl:for-each>
																											<xsl:for-each select="Latitude/EDXBABLE/ORIGINATOR/VALUE">
																												<Latitude>
																													<xsl:value-of select="."/>
																												</Latitude>
																											</xsl:for-each>
																											<xsl:for-each select="Longitude/EDXBABLE/ORIGINATOR/VALUE">
																												<Longitude>
																													<xsl:value-of select="."/>
																												</Longitude>
																											</xsl:for-each>
																										</GPSCooridinates>
																									</xsl:for-each>
																								</Location>
																							</xsl:for-each>
																						</TaxLocation>
																					</xsl:for-each>
																				</Tax>
																			</xsl:for-each>
																		</AllowOrCharge>
																	</xsl:for-each>
																</ListOfAllowOrCharge>
															</xsl:for-each>
														</ItemAllowancesOrCharges>
													</xsl:for-each>
													<xsl:for-each select="TotalValue">
														<TotalValue>
															<xsl:for-each select="MonetaryValue">
																<MonetaryValue>
																	<xsl:for-each select="MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE">
																		<MonetaryAmount>
																			<xsl:value-of select="."/>
																		</MonetaryAmount>
																	</xsl:for-each>
																	<xsl:for-each select="Currency">
																		<Currency>
																			<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<CurrencyCoded>
																					<xsl:value-of select="."/>
																				</CurrencyCoded>
																			</xsl:for-each>
																		</Currency>
																	</xsl:for-each>
																	<xsl:for-each select="RateOfExchangeDetail">
																		<RateOfExchangeDetail>
																			<xsl:for-each select="ReferenceCurrency">
																				<ReferenceCurrency>
																					<xsl:for-each select="Currency">
																						<Currency>
																							<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<CurrencyCoded>
																									<xsl:value-of select="."/>
																								</CurrencyCoded>
																							</xsl:for-each>
																							<xsl:for-each select="CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																								<CurrencyCodedOther>
																									<xsl:value-of select="."/>
																								</CurrencyCodedOther>
																							</xsl:for-each>
																						</Currency>
																					</xsl:for-each>
																				</ReferenceCurrency>
																			</xsl:for-each>
																			<xsl:for-each select="TargetCurrency">
																				<TargetCurrency>
																					<xsl:for-each select="Currency">
																						<Currency>
																							<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<CurrencyCoded>
																									<xsl:value-of select="."/>
																								</CurrencyCoded>
																							</xsl:for-each>
																							<xsl:for-each select="CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																								<CurrencyCodedOther>
																									<xsl:value-of select="."/>
																								</CurrencyCodedOther>
																							</xsl:for-each>
																						</Currency>
																					</xsl:for-each>
																				</TargetCurrency>
																			</xsl:for-each>
																			<xsl:for-each select="RateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
																				<RateOfExchange>
																					<xsl:value-of select="."/>
																				</RateOfExchange>
																			</xsl:for-each>
																			<xsl:for-each select="ListOfRateOfExchangeReference">
																				<ListOfRateOfExchangeReference>
																					<xsl:for-each select="ListOfReference">
																						<ListOfReference>
																							<xsl:for-each select="Reference">
																								<Reference>
																									<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																										<RefNum>
																											<xsl:value-of select="."/>
																										</RefNum>
																									</xsl:for-each>
																									<xsl:for-each select="RefDate/EDXBABLE/ORIGINATOR/VALUE">
																										<RefDate>
																											<xsl:value-of select="."/>
																										</RefDate>
																									</xsl:for-each>
																								</Reference>
																							</xsl:for-each>
																						</ListOfReference>
																					</xsl:for-each>
																				</ListOfRateOfExchangeReference>
																			</xsl:for-each>
																		</RateOfExchangeDetail>
																	</xsl:for-each>
																</MonetaryValue>
															</xsl:for-each>
														</TotalValue>
													</xsl:for-each>
												</PricingDetail>
											</xsl:for-each>
											<xsl:for-each select="DeliveryDetail">
												<DeliveryDetail>
													<xsl:for-each select="ShipToLocation">
														<ShipToLocation>
															<xsl:for-each select="Location">
																<Location>
																	<xsl:for-each select="LocationIdentifier">
																		<LocationIdentifier>
																			<xsl:for-each select="LocID">
																				<LocID>
																					<xsl:for-each select="Identifier">
																						<Identifier>
																							<xsl:for-each select="Agency">
																								<Agency>
																									<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCoded>
																											<xsl:value-of select="."/>
																										</AgencyCoded>
																									</xsl:for-each>
																								</Agency>
																							</xsl:for-each>
																							<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</xsl:for-each>
																				</LocID>
																			</xsl:for-each>
																		</LocationIdentifier>
																	</xsl:for-each>
																	<xsl:for-each select="ExternalAddressID/EDXBABLE/ORIGINATOR/VALUE">
																		<ExternalAddressID>
																			<xsl:value-of select="."/>
																		</ExternalAddressID>
																	</xsl:for-each>
																	<xsl:for-each select="NameAddress">
																		<NameAddress>
																			<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																				<Name1>
																					<xsl:value-of select="."/>
																				</Name1>
																			</xsl:for-each>
																			<xsl:for-each select="Identifier">
																				<Identifier>
																					<xsl:for-each select="Agency">
																						<Agency>
																							<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<AgencyCoded>
																									<xsl:value-of select="."/>
																								</AgencyCoded>
																							</xsl:for-each>
																						</Agency>
																					</xsl:for-each>
																					<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																						<Ident>
																							<xsl:value-of select="."/>
																						</Ident>
																					</xsl:for-each>
																				</Identifier>
																			</xsl:for-each>
																			<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																				<POBox>
																					<xsl:value-of select="."/>
																				</POBox>
																			</xsl:for-each>
																			<xsl:for-each select="Region">
																				<Region>
																					<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<RegionCoded>
																							<xsl:value-of select="."/>
																						</RegionCoded>
																					</xsl:for-each>
																				</Region>
																			</xsl:for-each>
																			<xsl:for-each select="Country">
																				<Country>
																					<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<CountryCoded>
																							<xsl:value-of select="."/>
																						</CountryCoded>
																					</xsl:for-each>
																					<xsl:for-each select="CountryCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<CountryCodedOther>
																							<xsl:value-of select="."/>
																						</CountryCodedOther>
																					</xsl:for-each>
																				</Country>
																			</xsl:for-each>
																			<xsl:for-each select="Timezone">
																				<Timezone>
																					<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<TimezoneCoded>
																							<xsl:value-of select="."/>
																						</TimezoneCoded>
																					</xsl:for-each>
																					<xsl:for-each select="TimezoneCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<TimezoneCodedOther>
																							<xsl:value-of select="."/>
																						</TimezoneCodedOther>
																					</xsl:for-each>
																				</Timezone>
																			</xsl:for-each>
																		</NameAddress>
																	</xsl:for-each>
																	<xsl:for-each select="GPSCooridinates">
																		<GPSCooridinates>
																			<xsl:for-each select="GPSSystem/EDXBABLE/ORIGINATOR/VALUE">
																				<GPSSystem>
																					<xsl:value-of select="."/>
																				</GPSSystem>
																			</xsl:for-each>
																			<xsl:for-each select="Latitude/EDXBABLE/ORIGINATOR/VALUE">
																				<Latitude>
																					<xsl:value-of select="."/>
																				</Latitude>
																			</xsl:for-each>
																			<xsl:for-each select="Longitude/EDXBABLE/ORIGINATOR/VALUE">
																				<Longitude>
																					<xsl:value-of select="."/>
																				</Longitude>
																			</xsl:for-each>
																		</GPSCooridinates>
																	</xsl:for-each>
																</Location>
															</xsl:for-each>
														</ShipToLocation>
													</xsl:for-each>
													<xsl:for-each select="ShipFromLocation">
														<ShipFromLocation>
															<xsl:for-each select="Location">
																<Location>
																	<xsl:for-each select="LocationIdentifier">
																		<LocationIdentifier>
																			<xsl:for-each select="LocID">
																				<LocID>
																					<xsl:for-each select="Identifier">
																						<Identifier>
																							<xsl:for-each select="Agency">
																								<Agency>
																									<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCoded>
																											<xsl:value-of select="."/>
																										</AgencyCoded>
																									</xsl:for-each>
																								</Agency>
																							</xsl:for-each>
																							<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</xsl:for-each>
																				</LocID>
																			</xsl:for-each>
																			<xsl:for-each select="LocationDescription/EDXBABLE/ORIGINATOR/VALUE">
																				<LocationDescription>
																					<xsl:value-of select="."/>
																				</LocationDescription>
																			</xsl:for-each>
																		</LocationIdentifier>
																	</xsl:for-each>
																	<xsl:for-each select="ExternalAddressID/EDXBABLE/ORIGINATOR/VALUE">
																		<ExternalAddressID>
																			<xsl:value-of select="."/>
																		</ExternalAddressID>
																	</xsl:for-each>
																	<xsl:for-each select="NameAddress">
																		<NameAddress>
																			<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																				<Name1>
																					<xsl:value-of select="."/>
																				</Name1>
																			</xsl:for-each>
																			<xsl:for-each select="Identifier">
																				<Identifier>
																					<xsl:for-each select="Agency">
																						<Agency>
																							<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<AgencyCoded>
																									<xsl:value-of select="."/>
																								</AgencyCoded>
																							</xsl:for-each>
																						</Agency>
																					</xsl:for-each>
																					<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																						<Ident>
																							<xsl:value-of select="."/>
																						</Ident>
																					</xsl:for-each>
																				</Identifier>
																			</xsl:for-each>
																			<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																				<POBox>
																					<xsl:value-of select="."/>
																				</POBox>
																			</xsl:for-each>
																			<xsl:for-each select="Region">
																				<Region>
																					<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<RegionCoded>
																							<xsl:value-of select="."/>
																						</RegionCoded>
																					</xsl:for-each>
																					<xsl:for-each select="RegionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<RegionCodedOther>
																							<xsl:value-of select="."/>
																						</RegionCodedOther>
																					</xsl:for-each>
																				</Region>
																			</xsl:for-each>
																			<xsl:for-each select="Country">
																				<Country>
																					<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<CountryCoded>
																							<xsl:value-of select="."/>
																						</CountryCoded>
																					</xsl:for-each>
																				</Country>
																			</xsl:for-each>
																			<xsl:for-each select="Timezone">
																				<Timezone>
																					<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<TimezoneCoded>
																							<xsl:value-of select="."/>
																						</TimezoneCoded>
																					</xsl:for-each>
																					<xsl:for-each select="TimezoneCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<TimezoneCodedOther>
																							<xsl:value-of select="."/>
																						</TimezoneCodedOther>
																					</xsl:for-each>
																				</Timezone>
																			</xsl:for-each>
																		</NameAddress>
																	</xsl:for-each>
																	<xsl:for-each select="GPSCooridinates">
																		<GPSCooridinates>
																			<xsl:for-each select="GPSSystem/EDXBABLE/ORIGINATOR/VALUE">
																				<GPSSystem>
																					<xsl:value-of select="."/>
																				</GPSSystem>
																			</xsl:for-each>
																			<xsl:for-each select="Latitude/EDXBABLE/ORIGINATOR/VALUE">
																				<Latitude>
																					<xsl:value-of select="."/>
																				</Latitude>
																			</xsl:for-each>
																			<xsl:for-each select="Longitude/EDXBABLE/ORIGINATOR/VALUE">
																				<Longitude>
																					<xsl:value-of select="."/>
																				</Longitude>
																			</xsl:for-each>
																		</GPSCooridinates>
																	</xsl:for-each>
																</Location>
															</xsl:for-each>
														</ShipFromLocation>
													</xsl:for-each>
													<xsl:for-each select="ListOfScheduleLine">
														<ListOfScheduleLine>
															<xsl:for-each select="ScheduleLine">
																<ScheduleLine>
																	<xsl:for-each select="ScheduleLineID/EDXBABLE/ORIGINATOR/VALUE">
																		<ScheduleLineID>
																			<xsl:value-of select="."/>
																		</ScheduleLineID>
																	</xsl:for-each>
																	<xsl:for-each select="Quantity">
																		<Quantity>
																			<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																				<QuantityValue>
																					<xsl:value-of select="."/>
																				</QuantityValue>
																			</xsl:for-each>
																			<xsl:for-each select="QuantityRange">
																				<QuantityRange>
																					<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																						<MinimumValue>
																							<xsl:value-of select="."/>
																						</MinimumValue>
																					</xsl:for-each>
																					<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																						<MaximumValue>
																							<xsl:value-of select="."/>
																						</MaximumValue>
																					</xsl:for-each>
																				</QuantityRange>
																			</xsl:for-each>
																			<xsl:for-each select="UnitOfMeasurement">
																				<UnitOfMeasurement>
																					<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<UOMCoded>
																							<xsl:value-of select="."/>
																						</UOMCoded>
																					</xsl:for-each>
																					<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<UOMCodedOther>
																							<xsl:value-of select="."/>
																						</UOMCodedOther>
																					</xsl:for-each>
																				</UnitOfMeasurement>
																			</xsl:for-each>
																		</Quantity>
																	</xsl:for-each>
																	<xsl:for-each select="RequestedDeliveryDate/EDXBABLE/ORIGINATOR/VALUE">
																		<RequestedDeliveryDate>
																			<xsl:value-of select="."/>
																		</RequestedDeliveryDate>
																	</xsl:for-each>
																	<xsl:for-each select="Transport">
																		<Transport>
																			<xsl:for-each select="TransportID/EDXBABLE/ORIGINATOR/VALUE">
																				<TransportID>
																					<xsl:value-of select="."/>
																				</TransportID>
																			</xsl:for-each>
																			<xsl:for-each select="TransportMode">
																				<TransportMode>
																					<xsl:for-each select="TransportModeCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<TransportModeCoded>
																							<xsl:value-of select="."/>
																						</TransportModeCoded>
																					</xsl:for-each>
																				</TransportMode>
																			</xsl:for-each>
																			<xsl:for-each select="TransportMeans">
																				<TransportMeans>
																					<xsl:for-each select="TransportMeansCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<TransportMeansCoded>
																							<xsl:value-of select="."/>
																						</TransportMeansCoded>
																					</xsl:for-each>
																				</TransportMeans>
																			</xsl:for-each>
																			<xsl:for-each select="CarrierID">
																				<CarrierID>
																					<xsl:for-each select="Identifier">
																						<Identifier>
																							<xsl:for-each select="Agency">
																								<Agency>
																									<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCoded>
																											<xsl:value-of select="."/>
																										</AgencyCoded>
																									</xsl:for-each>
																								</Agency>
																							</xsl:for-each>
																							<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</xsl:for-each>
																				</CarrierID>
																			</xsl:for-each>
																			<xsl:for-each select="ListOfTransportEquipment">
																				<ListOfTransportEquipment>
																					<xsl:for-each select="TransportEquipment">
																						<TransportEquipment>
																							<xsl:for-each select="EquipmentID/EDXBABLE/ORIGINATOR/VALUE">
																								<EquipmentID>
																									<xsl:value-of select="."/>
																								</EquipmentID>
																							</xsl:for-each>
																							<xsl:for-each select="Conditions">
																								<Conditions>
																									<xsl:for-each select="ListOfConditions">
																										<ListOfConditions>
																											<xsl:for-each select="ListOfDimension">
																												<ListOfDimension>
																													<xsl:for-each select="Dimension">
																														<Dimension>
																															<xsl:for-each select="Measurement">
																																<Measurement>
																																	<xsl:for-each select="MeasurementValue/EDXBABLE/ORIGINATOR/VALUE">
																																		<MeasurementValue>
																																			<xsl:value-of select="."/>
																																		</MeasurementValue>
																																	</xsl:for-each>
																																	<xsl:for-each select="MeasurementRange">
																																		<MeasurementRange>
																																			<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																																				<MinimumValue>
																																					<xsl:value-of select="."/>
																																				</MinimumValue>
																																			</xsl:for-each>
																																			<xsl:for-each select="MaximumValue">
																																				<MaximumValue/>
																																			</xsl:for-each>
																																		</MeasurementRange>
																																	</xsl:for-each>
																																	<xsl:for-each select="UnitOfMeasurement">
																																		<UnitOfMeasurement>
																																			<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																																				<UOMCoded>
																																					<xsl:value-of select="."/>
																																				</UOMCoded>
																																			</xsl:for-each>
																																			<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																																				<UOMCodedOther>
																																					<xsl:value-of select="."/>
																																				</UOMCodedOther>
																																			</xsl:for-each>
																																		</UnitOfMeasurement>
																																	</xsl:for-each>
																																</Measurement>
																															</xsl:for-each>
																															<xsl:for-each select="DimensionCoded/EDXBABLE/ORIGINATOR/VALUE">
																																<DimensionCoded>
																																	<xsl:value-of select="."/>
																																</DimensionCoded>
																															</xsl:for-each>
																														</Dimension>
																													</xsl:for-each>
																												</ListOfDimension>
																											</xsl:for-each>
																										</ListOfConditions>
																									</xsl:for-each>
																								</Conditions>
																							</xsl:for-each>
																							<xsl:for-each select="ListOfSealInfo">
																								<ListOfSealInfo>
																									<xsl:for-each select="SealInfo">
																										<SealInfo>
																											<xsl:for-each select="SealNumber/EDXBABLE/ORIGINATOR/VALUE">
																												<SealNumber>
																													<xsl:value-of select="."/>
																												</SealNumber>
																											</xsl:for-each>
																											<xsl:for-each select="SealIssuer">
																												<SealIssuer>
																													<xsl:for-each select="SealIssuerCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<SealIssuerCoded>
																															<xsl:value-of select="."/>
																														</SealIssuerCoded>
																													</xsl:for-each>
																													<xsl:for-each select="SealIssuerCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																														<SealIssuerCodedOther>
																															<xsl:value-of select="."/>
																														</SealIssuerCodedOther>
																													</xsl:for-each>
																												</SealIssuer>
																											</xsl:for-each>
																										</SealInfo>
																									</xsl:for-each>
																								</ListOfSealInfo>
																							</xsl:for-each>
																							<xsl:for-each select="ListOfEquipmentMeasurements">
																								<ListOfEquipmentMeasurements>
																									<xsl:for-each select="ListOfDimension">
																										<ListOfDimension>
																											<xsl:for-each select="Dimension">
																												<Dimension>
																													<xsl:for-each select="Measurement">
																														<Measurement>
																															<xsl:for-each select="MeasurementValue/EDXBABLE/ORIGINATOR/VALUE">
																																<MeasurementValue>
																																	<xsl:value-of select="."/>
																																</MeasurementValue>
																															</xsl:for-each>
																															<xsl:for-each select="MeasurementRange">
																																<MeasurementRange>
																																	<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																																		<MinimumValue>
																																			<xsl:value-of select="."/>
																																		</MinimumValue>
																																	</xsl:for-each>
																																	<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																																		<MaximumValue>
																																			<xsl:value-of select="."/>
																																		</MaximumValue>
																																	</xsl:for-each>
																																</MeasurementRange>
																															</xsl:for-each>
																															<xsl:for-each select="UnitOfMeasurement">
																																<UnitOfMeasurement>
																																	<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																																		<UOMCoded>
																																			<xsl:value-of select="."/>
																																		</UOMCoded>
																																	</xsl:for-each>
																																	<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																																		<UOMCodedOther>
																																			<xsl:value-of select="."/>
																																		</UOMCodedOther>
																																	</xsl:for-each>
																																</UnitOfMeasurement>
																															</xsl:for-each>
																														</Measurement>
																													</xsl:for-each>
																													<xsl:for-each select="DimensionCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<DimensionCoded>
																															<xsl:value-of select="."/>
																														</DimensionCoded>
																													</xsl:for-each>
																													<xsl:for-each select="DimensionCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																														<DimensionCodedOther>
																															<xsl:value-of select="."/>
																														</DimensionCodedOther>
																													</xsl:for-each>
																												</Dimension>
																											</xsl:for-each>
																										</ListOfDimension>
																									</xsl:for-each>
																								</ListOfEquipmentMeasurements>
																							</xsl:for-each>
																						</TransportEquipment>
																					</xsl:for-each>
																				</ListOfTransportEquipment>
																			</xsl:for-each>
																			<xsl:for-each select="TransitDirection">
																				<TransitDirection>
																					<xsl:for-each select="TransitDirectionCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<TransitDirectionCoded>
																							<xsl:value-of select="."/>
																						</TransitDirectionCoded>
																					</xsl:for-each>
																				</TransitDirection>
																			</xsl:for-each>
																		</Transport>
																	</xsl:for-each>
																	<xsl:for-each select="ListOfOtherDeliveryDate">
																		<ListOfOtherDeliveryDate>
																			<xsl:for-each select="ListOfDateCoded">
																				<ListOfDateCoded>
																					<xsl:for-each select="DateCoded">
																						<DateCoded>
																							<xsl:for-each select="Date/EDXBABLE/ORIGINATOR/VALUE">
																								<Date>
																									<xsl:value-of select="."/>
																								</Date>
																							</xsl:for-each>
																							<xsl:for-each select="DateQualifier">
																								<DateQualifier>
																									<xsl:for-each select="DateQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<DateQualifierCoded>
																											<xsl:value-of select="."/>
																										</DateQualifierCoded>
																									</xsl:for-each>
																								</DateQualifier>
																							</xsl:for-each>
																						</DateCoded>
																					</xsl:for-each>
																				</ListOfDateCoded>
																			</xsl:for-each>
																		</ListOfOtherDeliveryDate>
																	</xsl:for-each>
																	<xsl:for-each select="TransportReference/EDXBABLE/ORIGINATOR/VALUE">
																		<TransportReference>
																			<xsl:value-of select="."/>
																		</TransportReference>
																	</xsl:for-each>
																	<xsl:for-each select="ListOfShipToSubInformation">
																		<ListOfShipToSubInformation>
																			<xsl:for-each select="ShipToSubInformation">
																				<ShipToSubInformation>
																					<xsl:for-each select="ShipToSubLocation">
																						<ShipToSubLocation>
																							<xsl:for-each select="Location">
																								<Location>
																									<xsl:for-each select="LocationIdentifier">
																										<LocationIdentifier>
																											<xsl:for-each select="LocID">
																												<LocID>
																													<xsl:for-each select="Identifier">
																														<Identifier>
																															<xsl:for-each select="Agency">
																																<Agency>
																																	<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																																		<AgencyCoded>
																																			<xsl:value-of select="."/>
																																		</AgencyCoded>
																																	</xsl:for-each>
																																</Agency>
																															</xsl:for-each>
																															<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																																<Ident>
																																	<xsl:value-of select="."/>
																																</Ident>
																															</xsl:for-each>
																														</Identifier>
																													</xsl:for-each>
																												</LocID>
																											</xsl:for-each>
																										</LocationIdentifier>
																									</xsl:for-each>
																									<xsl:for-each select="ExternalAddressID/EDXBABLE/ORIGINATOR/VALUE">
																										<ExternalAddressID>
																											<xsl:value-of select="."/>
																										</ExternalAddressID>
																									</xsl:for-each>
																									<xsl:for-each select="NameAddress">
																										<NameAddress>
																											<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																												<Name1>
																													<xsl:value-of select="."/>
																												</Name1>
																											</xsl:for-each>
																											<xsl:for-each select="Identifier">
																												<Identifier>
																													<xsl:for-each select="Agency">
																														<Agency>
																															<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																																<AgencyCoded>
																																	<xsl:value-of select="."/>
																																</AgencyCoded>
																															</xsl:for-each>
																														</Agency>
																													</xsl:for-each>
																													<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																														<Ident>
																															<xsl:value-of select="."/>
																														</Ident>
																													</xsl:for-each>
																												</Identifier>
																											</xsl:for-each>
																											<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																												<POBox>
																													<xsl:value-of select="."/>
																												</POBox>
																											</xsl:for-each>
																											<xsl:for-each select="Region">
																												<Region>
																													<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<RegionCoded>
																															<xsl:value-of select="."/>
																														</RegionCoded>
																													</xsl:for-each>
																												</Region>
																											</xsl:for-each>
																											<xsl:for-each select="Country">
																												<Country>
																													<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<CountryCoded>
																															<xsl:value-of select="."/>
																														</CountryCoded>
																													</xsl:for-each>
																												</Country>
																											</xsl:for-each>
																											<xsl:for-each select="Timezone">
																												<Timezone>
																													<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																														<TimezoneCoded>
																															<xsl:value-of select="."/>
																														</TimezoneCoded>
																													</xsl:for-each>
																												</Timezone>
																											</xsl:for-each>
																										</NameAddress>
																									</xsl:for-each>
																									<xsl:for-each select="GPSCooridinates">
																										<GPSCooridinates>
																											<xsl:for-each select="GPSSystem/EDXBABLE/ORIGINATOR/VALUE">
																												<GPSSystem>
																													<xsl:value-of select="."/>
																												</GPSSystem>
																											</xsl:for-each>
																											<xsl:for-each select="Latitude/EDXBABLE/ORIGINATOR/VALUE">
																												<Latitude>
																													<xsl:value-of select="."/>
																												</Latitude>
																											</xsl:for-each>
																											<xsl:for-each select="Longitude/EDXBABLE/ORIGINATOR/VALUE">
																												<Longitude>
																													<xsl:value-of select="."/>
																												</Longitude>
																											</xsl:for-each>
																										</GPSCooridinates>
																									</xsl:for-each>
																								</Location>
																							</xsl:for-each>
																						</ShipToSubLocation>
																					</xsl:for-each>
																					<xsl:for-each select="ShipToSubQuantity">
																						<ShipToSubQuantity>
																							<xsl:for-each select="Quantity">
																								<Quantity>
																									<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																										<QuantityValue>
																											<xsl:value-of select="."/>
																										</QuantityValue>
																									</xsl:for-each>
																									<xsl:for-each select="QuantityRange">
																										<QuantityRange>
																											<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																												<MinimumValue>
																													<xsl:value-of select="."/>
																												</MinimumValue>
																											</xsl:for-each>
																											<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																												<MaximumValue>
																													<xsl:value-of select="."/>
																												</MaximumValue>
																											</xsl:for-each>
																										</QuantityRange>
																									</xsl:for-each>
																									<xsl:for-each select="UnitOfMeasurement">
																										<UnitOfMeasurement>
																											<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																												<UOMCoded>
																													<xsl:value-of select="."/>
																												</UOMCoded>
																											</xsl:for-each>
																											<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																												<UOMCodedOther>
																													<xsl:value-of select="."/>
																												</UOMCodedOther>
																											</xsl:for-each>
																										</UnitOfMeasurement>
																									</xsl:for-each>
																								</Quantity>
																							</xsl:for-each>
																						</ShipToSubQuantity>
																					</xsl:for-each>
																					<xsl:for-each select="SubLocationItemPackagingReference">
																						<SubLocationItemPackagingReference>
																							<xsl:for-each select="ItemPackagingReference">
																								<ItemPackagingReference>
																									<xsl:for-each select="PackageReference">
																										<PackageReference>
																											<xsl:for-each select="../../../../../../../ItemPackagingReference/PackageReference/Quantity">
																												<Quantity>
																													<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																														<QuantityValue>
																															<xsl:value-of select="."/>
																														</QuantityValue>
																													</xsl:for-each>
																													<xsl:for-each select="QuantityRange">
																														<QuantityRange>
																															<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																																<MinimumValue>
																																	<xsl:value-of select="."/>
																																</MinimumValue>
																															</xsl:for-each>
																															<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																																<MaximumValue>
																																	<xsl:value-of select="."/>
																																</MaximumValue>
																															</xsl:for-each>
																														</QuantityRange>
																													</xsl:for-each>
																													<xsl:for-each select="UnitOfMeasurement">
																														<UnitOfMeasurement>
																															<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																																<UOMCoded>
																																	<xsl:value-of select="."/>
																																</UOMCoded>
																															</xsl:for-each>
																														</UnitOfMeasurement>
																													</xsl:for-each>
																												</Quantity>
																											</xsl:for-each>
																											<xsl:for-each select="PackageIDReference/EDXBABLE/ORIGINATOR/VALUE">
																												<PackageIDReference>
																													<xsl:value-of select="."/>
																												</PackageIDReference>
																											</xsl:for-each>
																										</PackageReference>
																									</xsl:for-each>
																								</ItemPackagingReference>
																							</xsl:for-each>
																						</SubLocationItemPackagingReference>
																					</xsl:for-each>
																				</ShipToSubInformation>
																			</xsl:for-each>
																		</ListOfShipToSubInformation>
																	</xsl:for-each>
																</ScheduleLine>
															</xsl:for-each>
														</ListOfScheduleLine>
													</xsl:for-each>
													<xsl:for-each select="ItemPackagingReference">
														<ItemPackagingReference>
															<xsl:for-each select="PackageReference">
																<PackageReference>
																	<xsl:for-each select="Quantity">
																		<Quantity>
																			<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																				<QuantityValue>
																					<xsl:value-of select="."/>
																				</QuantityValue>
																			</xsl:for-each>
																			<QuantityRange>
																				<xsl:for-each select="QuantityRange/MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																					<MinimumValue>
																						<xsl:value-of select="."/>
																					</MinimumValue>
																				</xsl:for-each>
																				<xsl:for-each select="QuantityRange/MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																					<MaximumValue>
																						<xsl:value-of select="."/>
																					</MaximumValue>
																				</xsl:for-each>
																			</QuantityRange>
																			<xsl:for-each select="UnitOfMeasurement">
																				<UnitOfMeasurement>
																					<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<UOMCoded>
																							<xsl:value-of select="."/>
																						</UOMCoded>
																					</xsl:for-each>
																				</UnitOfMeasurement>
																			</xsl:for-each>
																		</Quantity>
																	</xsl:for-each>
																	<xsl:for-each select="PackageIDReference/EDXBABLE/ORIGINATOR/VALUE">
																		<PackageIDReference>
																			<xsl:value-of select="."/>
																		</PackageIDReference>
																	</xsl:for-each>
																	<xsl:for-each select="PackageReference">
																		<PackageReference>
																			<xsl:for-each select="Quantity">
																				<Quantity>
																					<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																						<QuantityValue>
																							<xsl:value-of select="."/>
																						</QuantityValue>
																					</xsl:for-each>
																					<QuantityRange>
																						<xsl:for-each select="QuantityRange/MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																							<MinimumValue>
																								<xsl:value-of select="."/>
																							</MinimumValue>
																						</xsl:for-each>
																						<xsl:for-each select="QuantityRange/MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																							<MaximumValue>
																								<xsl:value-of select="."/>
																							</MaximumValue>
																						</xsl:for-each>
																					</QuantityRange>
																					<xsl:for-each select="UnitOfMeasurement">
																						<UnitOfMeasurement>
																							<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<UOMCoded>
																									<xsl:value-of select="."/>
																								</UOMCoded>
																							</xsl:for-each>
																							<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																								<UOMCodedOther>
																									<xsl:value-of select="."/>
																								</UOMCodedOther>
																							</xsl:for-each>
																						</UnitOfMeasurement>
																					</xsl:for-each>
																				</Quantity>
																			</xsl:for-each>
																			<xsl:for-each select="PackageIDReference/EDXBABLE/ORIGINATOR/VALUE">
																				<PackageIDReference>
																					<xsl:value-of select="."/>
																				</PackageIDReference>
																			</xsl:for-each>
																			<xsl:for-each select="PackageReference">
																				<PackageReference>
																					<xsl:for-each select="Quantity">
																						<Quantity>
																							<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																								<QuantityValue>
																									<xsl:value-of select="."/>
																								</QuantityValue>
																							</xsl:for-each>
																							<xsl:for-each select="QuantityRange">
																								<QuantityRange>
																									<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																										<MinimumValue>
																											<xsl:value-of select="."/>
																										</MinimumValue>
																									</xsl:for-each>
																									<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																										<MaximumValue>
																											<xsl:value-of select="."/>
																										</MaximumValue>
																									</xsl:for-each>
																								</QuantityRange>
																							</xsl:for-each>
																							<xsl:for-each select="UnitOfMeasurement">
																								<UnitOfMeasurement>
																									<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<UOMCoded>
																											<xsl:value-of select="."/>
																										</UOMCoded>
																									</xsl:for-each>
																									<xsl:for-each select="UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																										<UOMCodedOther>
																											<xsl:value-of select="."/>
																										</UOMCodedOther>
																									</xsl:for-each>
																								</UnitOfMeasurement>
																							</xsl:for-each>
																						</Quantity>
																					</xsl:for-each>
																					<xsl:for-each select="PackageIDReference/EDXBABLE/ORIGINATOR/VALUE">
																						<PackageIDReference>
																							<xsl:value-of select="."/>
																						</PackageIDReference>
																					</xsl:for-each>
																				</PackageReference>
																			</xsl:for-each>
																		</PackageReference>
																	</xsl:for-each>
																</PackageReference>
															</xsl:for-each>
														</ItemPackagingReference>
													</xsl:for-each>
													<xsl:for-each select="SimplePackageNote/EDXBABLE/ORIGINATOR/VALUE">
														<SimplePackageNote>
															<xsl:value-of select="."/>
														</SimplePackageNote>
													</xsl:for-each>
													<xsl:for-each select="TermsOfDelivery">
														<TermsOfDelivery>
															<xsl:for-each select="TermsOfDeliveryFunctionCoded/EDXBABLE/ORIGINATOR/VALUE">
																<TermsOfDeliveryFunctionCoded>
																	<xsl:value-of select="."/>
																</TermsOfDeliveryFunctionCoded>
															</xsl:for-each>
															<xsl:for-each select="ShipmentMethodOfPaymentCoded/EDXBABLE/ORIGINATOR/VALUE">
																<ShipmentMethodOfPaymentCoded>
																	<xsl:value-of select="."/>
																</ShipmentMethodOfPaymentCoded>
															</xsl:for-each>
															<xsl:for-each select="Location">
																<Location>
																	<xsl:for-each select="LocationIdentifier">
																		<LocationIdentifier>
																			<xsl:for-each select="LocID">
																				<LocID>
																					<xsl:for-each select="Identifier">
																						<Identifier>
																							<xsl:for-each select="Agency">
																								<Agency>
																									<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																										<AgencyCoded>
																											<xsl:value-of select="."/>
																										</AgencyCoded>
																									</xsl:for-each>
																								</Agency>
																							</xsl:for-each>
																							<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																								<Ident>
																									<xsl:value-of select="."/>
																								</Ident>
																							</xsl:for-each>
																						</Identifier>
																					</xsl:for-each>
																				</LocID>
																			</xsl:for-each>
																		</LocationIdentifier>
																	</xsl:for-each>
																	<ExternalAddressID/>
																	<xsl:for-each select="NameAddress">
																		<NameAddress>
																			<xsl:for-each select="Name1/EDXBABLE/ORIGINATOR/VALUE">
																				<Name1>
																					<xsl:value-of select="."/>
																				</Name1>
																			</xsl:for-each>
																			<xsl:for-each select="Identifier">
																				<Identifier>
																					<xsl:for-each select="Agency">
																						<Agency>
																							<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																								<AgencyCoded>
																									<xsl:value-of select="."/>
																								</AgencyCoded>
																							</xsl:for-each>
																						</Agency>
																					</xsl:for-each>
																					<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																						<Ident>
																							<xsl:value-of select="."/>
																						</Ident>
																					</xsl:for-each>
																				</Identifier>
																			</xsl:for-each>
																			<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/VALUE">
																				<POBox>
																					<xsl:value-of select="."/>
																				</POBox>
																			</xsl:for-each>
																			<xsl:for-each select="Region">
																				<Region>
																					<xsl:for-each select="RegionCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<RegionCoded>
																							<xsl:value-of select="."/>
																						</RegionCoded>
																					</xsl:for-each>
																				</Region>
																			</xsl:for-each>
																			<xsl:for-each select="Country">
																				<Country>
																					<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<CountryCoded>
																							<xsl:value-of select="."/>
																						</CountryCoded>
																					</xsl:for-each>
																				</Country>
																			</xsl:for-each>
																			<xsl:for-each select="Timezone">
																				<Timezone>
																					<xsl:for-each select="TimezoneCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<TimezoneCoded>
																							<xsl:value-of select="."/>
																						</TimezoneCoded>
																					</xsl:for-each>
																				</Timezone>
																			</xsl:for-each>
																		</NameAddress>
																	</xsl:for-each>
																	<xsl:for-each select="GPSCooridinates">
																		<GPSCooridinates>
																			<xsl:for-each select="GPSSystem/EDXBABLE/ORIGINATOR/VALUE">
																				<GPSSystem>
																					<xsl:value-of select="."/>
																				</GPSSystem>
																			</xsl:for-each>
																			<xsl:for-each select="Latitude/EDXBABLE/ORIGINATOR/VALUE">
																				<Latitude>
																					<xsl:value-of select="."/>
																				</Latitude>
																			</xsl:for-each>
																			<xsl:for-each select="Longitude/EDXBABLE/ORIGINATOR/VALUE">
																				<Longitude>
																					<xsl:value-of select="."/>
																				</Longitude>
																			</xsl:for-each>
																		</GPSCooridinates>
																	</xsl:for-each>
																</Location>
															</xsl:for-each>
														</TermsOfDelivery>
													</xsl:for-each>
													<xsl:for-each select="CargoClassification">
														<CargoClassification>
															<xsl:for-each select="NatureOfGoods">
																<NatureOfGoods>
																	<xsl:for-each select="Identifier">
																		<Identifier>
																			<xsl:for-each select="Agency">
																				<Agency>
																					<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																				</Agency>
																			</xsl:for-each>
																			<xsl:for-each select="Ident/EDXBABLE/ORIGINATOR/VALUE">
																				<Ident>
																					<xsl:value-of select="."/>
																				</Ident>
																			</xsl:for-each>
																		</Identifier>
																	</xsl:for-each>
																</NatureOfGoods>
															</xsl:for-each>
														</CargoClassification>
													</xsl:for-each>
												</DeliveryDetail>
											</xsl:for-each>
											<xsl:for-each select="LineItemNote/EDXBABLE/ORIGINATOR/VALUE">
												<LineItemNote>
													<xsl:value-of select="."/>
												</LineItemNote>
											</xsl:for-each>
											<xsl:for-each select="ListOfStructuredNote">
												<ListOfStructuredNote>
													<xsl:for-each select="StructuredNote">
														<StructuredNote>
															<xsl:for-each select="GeneralNote/EDXBABLE/ORIGINATOR/VALUE">
																<GeneralNote>
																	<xsl:value-of select="."/>
																</GeneralNote>
															</xsl:for-each>
															<xsl:for-each select="NoteID/EDXBABLE/ORIGINATOR/VALUE">
																<NoteID>
																	<xsl:value-of select="."/>
																</NoteID>
															</xsl:for-each>
															<xsl:for-each select="Agency">
																<Agency>
																	<xsl:for-each select="AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																		<AgencyCoded>
																			<xsl:value-of select="."/>
																		</AgencyCoded>
																	</xsl:for-each>
																	<xsl:for-each select="AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																		<AgencyCodedOther>
																			<xsl:value-of select="."/>
																		</AgencyCodedOther>
																	</xsl:for-each>
																</Agency>
															</xsl:for-each>
														</StructuredNote>
													</xsl:for-each>
												</ListOfStructuredNote>
											</xsl:for-each>
											<xsl:for-each select="LineItemAttachments">
												<LineItemAttachments>
													<xsl:for-each select="ListOfAttachment">
														<ListOfAttachment>
															<xsl:for-each select="Attachment">
																<Attachment>
																	<xsl:for-each select="AttachmentPurpose/EDXBABLE/ORIGINATOR/VALUE">
																		<AttachmentPurpose>
																			<xsl:value-of select="."/>
																		</AttachmentPurpose>
																	</xsl:for-each>
																	<xsl:for-each select="Language">
																		<Language>
																			<xsl:for-each select="LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<LanguageCoded>
																					<xsl:value-of select="."/>
																				</LanguageCoded>
																			</xsl:for-each>
																		</Language>
																	</xsl:for-each>
																	<xsl:for-each select="AttachmentLocation/EDXBABLE/ORIGINATOR/VALUE">
																		<AttachmentLocation>
																			<xsl:value-of select="."/>
																		</AttachmentLocation>
																	</xsl:for-each>
																</Attachment>
															</xsl:for-each>
														</ListOfAttachment>
													</xsl:for-each>
												</LineItemAttachments>
											</xsl:for-each>
										</ItemDetail>
									</OriginalItemDetail>
                  <!--Ignore the ChangeOrderItemDetail sections-->
									<xsl:for-each select="ChangeOrderItemDetail">
										<ChangeOrderItemDetail>
											<ItemDetailChangeCoded/>
										</ChangeOrderItemDetail>
									</xsl:for-each>
                  <!--Ignore the ItemDetailChanges sections-->
									<xsl:for-each select="ItemDetailChanges">
										<ItemDetailChanges>
											<xsl:for-each select=".">
												<ItemDetail>
													<xsl:for-each select="BaseItemDetail">
														<BaseItemDetail>
															<xsl:for-each select="LineItemNum">
																<LineItemNum>
																	<xsl:for-each select="BuyerLineItemNum/EDXBABLE/ORIGINATOR/VALUE">
																		<BuyerLineItemNum>
																			<xsl:value-of select="."/>
																		</BuyerLineItemNum>
																	</xsl:for-each>
																	<xsl:for-each select="BuyerLineItemNum/EDXBABLE/DESTINATION/RESOLVEDVALUE">
																		<SellerLineItemNum>
																			<xsl:value-of select="."/>
																		</SellerLineItemNum>
																	</xsl:for-each>
																</LineItemNum>
															</xsl:for-each>
															<LineItemType>
																<xsl:for-each select="LineItemType/LineItemTypeCoded/EDXBABLE/DESTINATION/RESOLVEDVALUE">
																	<LineItemTypeCoded>
																		<xsl:value-of select="."/>
																	</LineItemTypeCoded>
																</xsl:for-each>
															</LineItemType>
															<ItemIdentifiers>
																<PartNumbers>
																	<SellerPartNumber>
																		<PartNum>
																			<xsl:for-each select="ItemIdentifiers/PartNumbers/SellerPartNumber/PartNum/PartID/EDXBABLE/DESTINATION/RESOLVEDVALUE">
																				<PartID>
																					<xsl:value-of select="."/>
																																									</PartID>
																			</xsl:for-each>
																		</PartNum>
																	</SellerPartNumber>
																	<BuyerPartNumber>
																		<PartNum>
																			<xsl:for-each select="ItemIdentifiers/PartNumbers/BuyerPartNumber/PartNum/PartID/EDXBABLE/ORIGINATOR/VALUE">
																				<PartID>
																					<xsl:value-of select="."/>
																				</PartID>
																			</xsl:for-each>
																			<xsl:for-each select="ItemIdentifiers/PartNumbers/BuyerPartNumber/PartNum/PartIDExt/EDXBABLE/ORIGINATOR/VALUE">
																				<PartIDExt>
																					<xsl:value-of select="."/>
																				</PartIDExt>
																			</xsl:for-each>
																		</PartNum>
																	</BuyerPartNumber>
																	<ManufacturerPartNumber>
																		<xsl:for-each select="ItemIdentifiers/PartNumbers/ManufacturerPartNumber/PartID/EDXBABLE/DESTINATION/RESOLVEDVALUE">
																			<PartID>
																				<xsl:value-of select="."/>
																			</PartID>
																		</xsl:for-each>
																		<xsl:for-each select="ItemIdentifiers/PartNumbers/ManufacturerPartNumber/PartIDExt/EDXBABLE/DESTINATION/RESOLVEDVALUE">
																			<PartIDExt>
																				<xsl:value-of select="."/>
																			</PartIDExt>
																		</xsl:for-each>
																	</ManufacturerPartNumber>
																</PartNumbers>
																<xsl:for-each select="ItemIdentifiers/ItemDescription/EDXBABLE/ORIGINATOR/VALUE">
																	<ItemDescription>
																		<xsl:value-of select="."/>
																	</ItemDescription>
																</xsl:for-each>
																<CommodityCode>
																	<Identifier>
																		<Agency>
																			<xsl:for-each select="ItemIdentifiers/CommodityCode/Identifier/Agency/AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<AgencyCoded>
																					<xsl:value-of select="."/>
																				</AgencyCoded>
																			</xsl:for-each>
																			<xsl:for-each select="ItemIdentifiers/CommodityCode/Identifier/Agency/AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<AgencyCodedOther>
																					<xsl:value-of select="."/>
																				</AgencyCodedOther>
																			</xsl:for-each>
																		</Agency>
																		<xsl:for-each select="ItemIdentifiers/CommodityCode/Identifier/Ident/EDXBABLE/ORIGINATOR/VALUE">
																			<Ident>
																				<xsl:value-of select="."/>
																			</Ident>
																		</xsl:for-each>
																	</Identifier>
																</CommodityCode>
															</ItemIdentifiers>
															<TotalQuantity>
																<Quantity>
																	<xsl:for-each select="TotalQuantity/Quantity/QuantityValue/EDXBABLE/DESTINATION/RESOLVEDVALUE">
																		<QuantityValue>
																			<xsl:value-of select="."/>
																		</QuantityValue>
																	</xsl:for-each>
																	<QuantityRange>
																		<MinimumValue/>
																		<MaximumValue/>
																	</QuantityRange>
																	<UnitOfMeasurement>
																		<xsl:for-each select="TotalQuantity/Quantity/UnitOfMeasurement/UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<UOMCoded>
																				<xsl:value-of select="."/>
																			</UOMCoded>
																		</xsl:for-each>
																	</UnitOfMeasurement>
																</Quantity>
															</TotalQuantity>
															<xsl:for-each select="OffCatalogFlag/EDXBABLE/ORIGINATOR/VALUE">
																<OffCatalogFlag>
																	<xsl:value-of select="."/>
																</OffCatalogFlag>
															</xsl:for-each>
														</BaseItemDetail>
													</xsl:for-each>
													<PricingDetail>
														<xsl:for-each select="PricingDetail/ListOfPrice">
															<ListOfPrice>
																<Price>
																	<UnitPrice>
																		<xsl:for-each select="Price/UnitPrice/UnitPriceValue/EDXBABLE/ORIGINATOR/VALUE">
																			<UnitPriceValue>
																				<xsl:value-of select="."/>
																			</UnitPriceValue>
																		</xsl:for-each>
																		<Currency>
																			<xsl:for-each select="Price/UnitPrice/Currency/CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<CurrencyCoded>
																					<xsl:value-of select="."/>
																				</CurrencyCoded>
																			</xsl:for-each>
																			<xsl:for-each select="Price/UnitPrice/Currency/CurrencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<CurrencyCodedOther>
																					<xsl:value-of select="."/>
																				</CurrencyCodedOther>
																			</xsl:for-each>
																		</Currency>
																		<UnitOfMeasurement>
																			<xsl:for-each select="Price/UnitPrice/UnitOfMeasurement/UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<UOMCoded>
																					<xsl:value-of select="."/>
																				</UOMCoded>
																			</xsl:for-each>
																			<xsl:for-each select="Price/UnitPrice/UnitOfMeasurement/UOMCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																				<UOMCodedOther>
																					<xsl:value-of select="."/>
																				</UOMCodedOther>
																			</xsl:for-each>
																		</UnitOfMeasurement>
																	</UnitPrice>
																	<PriceBasisQuantity>
																		<Quantity>
																			<QuantityValue/>
																			<QuantityRange>
																				<xsl:for-each select="Price/PriceBasisQuantity/Quantity/QuantityRange/MinimumValue/EDXBABLE/DESTINATION/RESOLVEDVALUE">
																					<MinimumValue>
																						<xsl:value-of select="."/>
																					</MinimumValue>
																				</xsl:for-each>
																				<xsl:for-each select="Price/PriceBasisQuantity/Quantity/QuantityRange/MaximumValue/EDXBABLE/DESTINATION/RESOLVEDVALUE">
																					<MaximumValue>
																						<xsl:value-of select="."/>
																					</MaximumValue>
																				</xsl:for-each>
																			</QuantityRange>
																			<UnitOfMeasurement>
																				<xsl:for-each select="Price/PriceBasisQuantity/Quantity/UnitOfMeasurement/UOMCoded/EDXBABLE/DESTINATION/RESOLVEDVALUE">
																					<UOMCoded>
																						<xsl:value-of select="."/>
																					</UOMCoded>
																				</xsl:for-each>
																				<xsl:for-each select="Price/PriceBasisQuantity/Quantity/UnitOfMeasurement/UOMCodedOther/EDXBABLE/DESTINATION/RESOLVEDVALUE">
																					<UOMCodedOther>
																						<xsl:value-of select="."/>
																					</UOMCodedOther>
																				</xsl:for-each>
																			</UnitOfMeasurement>
																		</Quantity>
																	</PriceBasisQuantity>
																	<ValidityDates>
																		<xsl:for-each select="Price/ValidityDates/StartDate/EDXBABLE/ORIGINATOR/VALUE">
																			<StartDate>
																				<xsl:value-of select="."/>
																			</StartDate>
																		</xsl:for-each>
																		<xsl:for-each select="Price/ValidityDates/EndDate/EDXBABLE/ORIGINATOR/VALUE">
																			<EndDate>
																				<xsl:value-of select="."/>
																			</EndDate>
																		</xsl:for-each>
																	</ValidityDates>
																</Price>
															</ListOfPrice>
														</xsl:for-each>
														<Tax>
															<xsl:for-each select="PricingDetail/Tax/TaxFunctionQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																<TaxFunctionQualifierCoded>
																	<xsl:value-of select="."/>
																</TaxFunctionQualifierCoded>
															</xsl:for-each>
															<xsl:for-each select="PricingDetail/Tax/TaxCategoryCoded/EDXBABLE/ORIGINATOR/VALUE">
																<TaxCategoryCoded>
																	<xsl:value-of select="."/>
																</TaxCategoryCoded>
															</xsl:for-each>
															<xsl:for-each select="PricingDetail/Tax/TaxTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
																<TaxTypeCoded>
																	<xsl:value-of select="."/>
																</TaxTypeCoded>
															</xsl:for-each>
															<xsl:for-each select="PricingDetail/Tax/TaxPercent/EDXBABLE/ORIGINATOR/VALUE">
																<TaxPercent>
																	<xsl:value-of select="."/>
																</TaxPercent>
															</xsl:for-each>
															<xsl:for-each select="PricingDetail/Tax/TaxableAmount/EDXBABLE/ORIGINATOR/VALUE">
																<TaxableAmount>
																	<xsl:value-of select="."/>
																</TaxableAmount>
															</xsl:for-each>
															<xsl:for-each select="PricingDetail/Tax/TaxAmount/EDXBABLE/ORIGINATOR/VALUE">
																<TaxAmount>
																	<xsl:value-of select="."/>
																</TaxAmount>
															</xsl:for-each>
															<TaxLocation>
																<Location>
																	<LocationIdentifier>
																		<LocID>
																			<Identifier>
																				<Agency>
																					<xsl:for-each select="PricingDetail/Tax/TaxLocation/Location/LocationIdentifier/LocID/Identifier/Agency/AgencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCoded>
																							<xsl:value-of select="."/>
																						</AgencyCoded>
																					</xsl:for-each>
																					<xsl:for-each select="PricingDetail/Tax/TaxLocation/Location/LocationIdentifier/LocID/Identifier/Agency/AgencyCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																						<AgencyCodedOther>
																							<xsl:value-of select="."/>
																						</AgencyCodedOther>
																					</xsl:for-each>
																				</Agency>
																				<xsl:for-each select="PricingDetail/Tax/TaxLocation/Location/LocationIdentifier/LocID/Identifier/Ident/EDXBABLE/ORIGINATOR/VALUE">
																					<Ident>
																						<xsl:value-of select="."/>
																					</Ident>
																				</xsl:for-each>
																			</Identifier>
																		</LocID>
																	</LocationIdentifier>
																	<ExternalAddressID/>
																	<NameAddress>
																		<Name1/>
																	</NameAddress>
																</Location>
															</TaxLocation>
														</Tax>
														<ItemAllowancesOrCharges>
															<xsl:for-each select="PricingDetail/ItemAllowancesOrCharges/ListOfAllowOrCharge">
																<ListOfAllowOrCharge>
																	<AllowOrCharge>
																		<xsl:for-each select="AllowOrCharge/IndicatorCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<IndicatorCoded>
																				<xsl:value-of select="."/>
																			</IndicatorCoded>
																		</xsl:for-each>
																		<xsl:for-each select="AllowOrCharge/BasisCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<BasisCoded>
																				<xsl:value-of select="."/>
																			</BasisCoded>
																		</xsl:for-each>
																		<xsl:for-each select="AllowOrCharge/MethodOfHandlingCoded/EDXBABLE/ORIGINATOR/VALUE">
																			<MethodOfHandlingCoded>
																				<xsl:value-of select="."/>
																			</MethodOfHandlingCoded>
																		</xsl:for-each>
																		<xsl:for-each select="AllowOrCharge/MethodOfHandlingCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																			<MethodOfHandlingCodedOther>
																				<xsl:value-of select="."/>
																			</MethodOfHandlingCodedOther>
																		</xsl:for-each>
																		<AllowanceOrChargeDescription>
																			<AllowOrChgDesc>
																				<xsl:for-each select="AllowOrCharge/AllowanceOrChargeDescription/AllowOrChgDesc/ListOfDescription">
																					<ListOfDescription>
																						<Description>
																							<xsl:for-each select="Description/DescriptionText/EDXBABLE/ORIGINATOR/VALUE">
																								<DescriptionText>
																									<xsl:value-of select="."/>
																								</DescriptionText>
																							</xsl:for-each>
																							<Language>
																								<xsl:for-each select="Description/Language/LanguageCoded/EDXBABLE/ORIGINATOR/VALUE">
																									<LanguageCoded>
																										<xsl:value-of select="."/>
																									</LanguageCoded>
																								</xsl:for-each>
																							</Language>
																						</Description>
																					</ListOfDescription>
																				</xsl:for-each>
																				<xsl:for-each select="AllowOrCharge/AllowanceOrChargeDescription/AllowOrChgDesc/ServiceCoded/EDXBABLE/ORIGINATOR/VALUE">
																					<ServiceCoded>
																						<xsl:value-of select="."/>
																					</ServiceCoded>
																				</xsl:for-each>
																			</AllowOrChgDesc>
																		</AllowanceOrChargeDescription>
																		<BasisQuantityRange>
																			<Quantity>
																				<QuantityValue/>
																				<QuantityRange>
																					<MinimumValue/>
																					<MaximumValue/>
																				</QuantityRange>
																				<UnitOfMeasurement>
																					<UOMCoded/>
																				</UnitOfMeasurement>
																			</Quantity>
																		</BasisQuantityRange>
																		<BasisMonetaryRange>
																			<MonetaryRange>
																				<MinimumMonetaryValue/>
																				<MaximumMonetaryValue/>
																				<Currency>
																					<CurrencyCoded/>
																				</Currency>
																			</MonetaryRange>
																			<MonetaryLimit>
																				<MonetaryLimitValue/>
																				<Currency>
																					<CurrencyCoded/>
																				</Currency>
																			</MonetaryLimit>
																		</BasisMonetaryRange>
																		<TypeOfAllowanceOrCharge>
																			<QuantityAllowanceOrCharge>
																				<Quantity>
																					<QuantityValue/>
																					<QuantityRange>
																						<MinimumValue/>
																						<MaximumValue/>
																					</QuantityRange>
																					<UnitOfMeasurement>
																						<UOMCoded/>
																					</UnitOfMeasurement>
																				</Quantity>
																				<Rate>
																					<RatePerUnit>
																						<UnitPrice>
																							<UnitPriceValue/>
																						</UnitPrice>
																					</RatePerUnit>
																					<UnitPriceBasis/>
																				</Rate>
																			</QuantityAllowanceOrCharge>
																			<PercentageAllowanceOrCharge>
																				<PercentQualifier>
																					<xsl:for-each select="AllowOrCharge/TypeOfAllowanceOrCharge/PercentageAllowanceOrCharge/PercentQualifier/PercentQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<PercentQualifierCoded>
																							<xsl:value-of select="."/>
																						</PercentQualifierCoded>
																					</xsl:for-each>
																				</PercentQualifier>
																				<xsl:for-each select="AllowOrCharge/TypeOfAllowanceOrCharge/PercentageAllowanceOrCharge/Percent/EDXBABLE/ORIGINATOR/VALUE">
																					<Percent>
																						<xsl:value-of select="."/>
																					</Percent>
																				</xsl:for-each>
																			</PercentageAllowanceOrCharge>
																			<MonetaryValue>
																				<MonetaryAmount/>
																			</MonetaryValue>
																		</TypeOfAllowanceOrCharge>
																	</AllowOrCharge>
																</ListOfAllowOrCharge>
															</xsl:for-each>
														</ItemAllowancesOrCharges>
														<TotalValue>
															<MonetaryValue>
																<xsl:for-each select="PricingDetail/ItemAllowancesOrCharges/ListOfAllowOrCharge/AllowOrCharge/TypeOfAllowanceOrCharge/MonetaryValue/MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE">
																	<MonetaryAmount>
																		<xsl:value-of select="."/>
																	</MonetaryAmount>
																</xsl:for-each>
																<Currency>
																	<xsl:for-each select="PricingDetail/ItemAllowancesOrCharges/ListOfAllowOrCharge/AllowOrCharge/TypeOfAllowanceOrCharge/MonetaryValue/Currency/CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																		<CurrencyCoded>
																			<xsl:value-of select="."/>
																		</CurrencyCoded>
																	</xsl:for-each>
																</Currency>
															</MonetaryValue>
														</TotalValue>
													</PricingDetail>
													<DeliveryDetail>
														<xsl:for-each select="DeliveryDetail/ListOfScheduleLine">
															<ListOfScheduleLine>
																<ScheduleLine>
																	<xsl:for-each select="ScheduleLine/ScheduleLineID/EDXBABLE/ORIGINATOR/VALUE">
																		<ScheduleLineID>
																			<xsl:value-of select="."/>
																		</ScheduleLineID>
																	</xsl:for-each>
																	<Quantity>
																		<xsl:for-each select="ScheduleLine/Quantity/QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																			<QuantityValue>
																				<xsl:value-of select="."/>
																			</QuantityValue>
																		</xsl:for-each>
																		<QuantityRange>
																			<MinimumValue/>
																			<MaximumValue/>
																		</QuantityRange>
																		<UnitOfMeasurement>
																			<xsl:for-each select="ScheduleLine/Quantity/UnitOfMeasurement/UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<UOMCoded>
																					<xsl:value-of select="."/>
																				</UOMCoded>
																			</xsl:for-each>
																		</UnitOfMeasurement>
																	</Quantity>
																	<ListOfOtherDeliveryDate>
																		<ListOfDateCoded>
																			<DateCoded>
																				<Date></Date>
																				<DateQualifier>
																					<DateQualifierCoded></DateQualifierCoded>
																				</DateQualifier>
																			</DateCoded>
																		</ListOfDateCoded>
																	</ListOfOtherDeliveryDate>
																	<Transport>
																		<TransportID/>
																	</Transport>
																	<xsl:for-each select="ScheduleLine/RequestedDeliveryDate/EDXBABLE/ORIGINATOR/VALUE">
																		<RequestedDeliveryDate>
																			<xsl:value-of select="."/>
																		</RequestedDeliveryDate>
																	</xsl:for-each>
																	<TransportReference/>
																</ScheduleLine>
															</ListOfScheduleLine>
														</xsl:for-each>
													</DeliveryDetail>
												</ItemDetail>
											</xsl:for-each>
										</ItemDetailChanges>
									</xsl:for-each>
                  <!-- Ignor the ChangeOrderItemDetail fror now -->
									<xsl:for-each select="ChangeOrderItemDetail">
										<ChangeOrderItemDetail>
											<ItemDetailChangeCoded/>
										</ChangeOrderItemDetail>
									</xsl:for-each>
									<xsl:for-each select="LineItemNote/EDXBABLE/DESTINATION/RESOLVEDVALUE">
										<LineItemNote>
											<xsl:value-of select="."/>
										</LineItemNote>
									</xsl:for-each>
								</OrderResponseItemDetail>
							</xsl:for-each>
						</ListOfOrderResponseItemDetail>
					</xsl:for-each>
				</OrderResponseDetail>
			</xsl:for-each>
			<OrderResponseSummary>
				<OriginalOrderSummary>
					<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/Order/OrderSummary">
						<OrderSummary>
							<xsl:for-each select="NumberOfLines/EDXBABLE/ORIGINATOR/VALUE">
								<NumberOfLines>
									<xsl:value-of select="."/>
								</NumberOfLines>
							</xsl:for-each>
							<xsl:for-each select="TotalTax">
								<TotalTax>
									<xsl:for-each select="MonetaryValue">
										<MonetaryValue>
											<xsl:for-each select="MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE">
												<MonetaryAmount>
													<xsl:value-of select="."/>
												</MonetaryAmount>
											</xsl:for-each>
											<xsl:for-each select="Currency">
												<Currency>
													<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
														<CurrencyCoded>
															<xsl:value-of select="."/>
														</CurrencyCoded>
													</xsl:for-each>
												</Currency>
											</xsl:for-each>
											<xsl:for-each select="RateOfExchangeDetail">
												<RateOfExchangeDetail>
													<xsl:for-each select="ReferenceCurrency">
														<ReferenceCurrency>
															<xsl:for-each select="Currency">
																<Currency>
																	<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																		<CurrencyCoded>
																			<xsl:value-of select="."/>
																		</CurrencyCoded>
																	</xsl:for-each>
																</Currency>
															</xsl:for-each>
														</ReferenceCurrency>
													</xsl:for-each>
													<xsl:for-each select="TargetCurrency">
														<TargetCurrency>
															<xsl:for-each select="Currency">
																<Currency>
																	<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																		<CurrencyCoded>
																			<xsl:value-of select="."/>
																		</CurrencyCoded>
																	</xsl:for-each>
																</Currency>
															</xsl:for-each>
														</TargetCurrency>
													</xsl:for-each>
													<xsl:for-each select="RateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
														<RateOfExchange>
															<xsl:value-of select="."/>
														</RateOfExchange>
													</xsl:for-each>
													<xsl:for-each select="ListOfRateOfExchangeReference">
														<ListOfRateOfExchangeReference>
															<xsl:for-each select="ListOfReference">
																<ListOfReference>
																	<xsl:for-each select="Reference">
																		<Reference>
																			<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																				<RefNum>
																					<xsl:value-of select="."/>
																				</RefNum>
																			</xsl:for-each>
																		</Reference>
																	</xsl:for-each>
																</ListOfReference>
															</xsl:for-each>
														</ListOfRateOfExchangeReference>
													</xsl:for-each>
												</RateOfExchangeDetail>
											</xsl:for-each>
										</MonetaryValue>
									</xsl:for-each>
								</TotalTax>
							</xsl:for-each>
							<xsl:for-each select="TotalAmount">
								<TotalAmount>
									<xsl:for-each select="MonetaryValue">
										<MonetaryValue>
											<xsl:for-each select="MonetaryAmount/EDXBABLE/ORIGINATOR/VALUE">
												<MonetaryAmount>
													<xsl:value-of select="."/>
												</MonetaryAmount>
											</xsl:for-each>
											<xsl:for-each select="Currency">
												<Currency>
													<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
														<CurrencyCoded>
															<xsl:value-of select="."/>
														</CurrencyCoded>
													</xsl:for-each>
												</Currency>
											</xsl:for-each>
											<xsl:for-each select="RateOfExchangeDetail">
												<RateOfExchangeDetail>
													<xsl:for-each select="ReferenceCurrency">
														<ReferenceCurrency>
															<xsl:for-each select="Currency">
																<Currency>
																	<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																		<CurrencyCoded>
																			<xsl:value-of select="."/>
																		</CurrencyCoded>
																	</xsl:for-each>
																</Currency>
															</xsl:for-each>
														</ReferenceCurrency>
													</xsl:for-each>
													<xsl:for-each select="TargetCurrency">
														<TargetCurrency>
															<xsl:for-each select="Currency">
																<Currency>
																	<xsl:for-each select="CurrencyCoded/EDXBABLE/ORIGINATOR/VALUE">
																		<CurrencyCoded>
																			<xsl:value-of select="."/>
																		</CurrencyCoded>
																	</xsl:for-each>
																</Currency>
															</xsl:for-each>
														</TargetCurrency>
													</xsl:for-each>
													<xsl:for-each select="RateOfExchange/EDXBABLE/ORIGINATOR/VALUE">
														<RateOfExchange>
															<xsl:value-of select="."/>
														</RateOfExchange>
													</xsl:for-each>
													<xsl:for-each select="ListOfRateOfExchangeReference">
														<ListOfRateOfExchangeReference>
															<xsl:for-each select="ListOfReference">
																<ListOfReference>
																	<xsl:for-each select="Reference">
																		<Reference>
																			<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																				<RefNum>
																					<xsl:value-of select="."/>
																				</RefNum>
																			</xsl:for-each>
																		</Reference>
																	</xsl:for-each>
																</ListOfReference>
															</xsl:for-each>
														</ListOfRateOfExchangeReference>
													</xsl:for-each>
												</RateOfExchangeDetail>
											</xsl:for-each>
										</MonetaryValue>
									</xsl:for-each>
								</TotalAmount>
							</xsl:for-each>
							<xsl:for-each select="TransportPackagingTotals">
								<TransportPackagingTotals>
									<xsl:for-each select="TotalGrossWeight">
										<TotalGrossWeight>
											<xsl:for-each select="Measurement">
												<Measurement>
													<xsl:for-each select="MeasurementValue/EDXBABLE/ORIGINATOR/VALUE">
														<MeasurementValue>
															<xsl:value-of select="."/>
														</MeasurementValue>
													</xsl:for-each>
													<xsl:for-each select="MeasurementRange">
														<MeasurementRange>
															<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																<MinimumValue>
																	<xsl:value-of select="."/>
																</MinimumValue>
															</xsl:for-each>
															<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																<MaximumValue>
																	<xsl:value-of select="."/>
																</MaximumValue>
															</xsl:for-each>
														</MeasurementRange>
													</xsl:for-each>
													<xsl:for-each select="UnitOfMeasurement">
														<UnitOfMeasurement>
															<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																<UOMCoded>
																	<xsl:value-of select="."/>
																</UOMCoded>
															</xsl:for-each>
														</UnitOfMeasurement>
													</xsl:for-each>
												</Measurement>
											</xsl:for-each>
										</TotalGrossWeight>
									</xsl:for-each>
									<xsl:for-each select="TotalNetWeight">
										<TotalNetWeight>
											<xsl:for-each select="Measurement">
												<Measurement>
													<xsl:for-each select="MeasurementValue/EDXBABLE/ORIGINATOR/VALUE">
														<MeasurementValue>
															<xsl:value-of select="."/>
														</MeasurementValue>
													</xsl:for-each>
													<xsl:for-each select="MeasurementRange">
														<MeasurementRange>
															<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																<MinimumValue>
																	<xsl:value-of select="."/>
																</MinimumValue>
															</xsl:for-each>
															<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																<MaximumValue>
																	<xsl:value-of select="."/>
																</MaximumValue>
															</xsl:for-each>
														</MeasurementRange>
													</xsl:for-each>
													<xsl:for-each select="UnitOfMeasurement">
														<UnitOfMeasurement>
															<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																<UOMCoded>
																	<xsl:value-of select="."/>
																</UOMCoded>
															</xsl:for-each>
														</UnitOfMeasurement>
													</xsl:for-each>
												</Measurement>
											</xsl:for-each>
										</TotalNetWeight>
									</xsl:for-each>
									<xsl:for-each select="TotalNetNetWeight">
										<TotalNetNetWeight>
											<xsl:for-each select="Measurement">
												<Measurement>
													<xsl:for-each select="MeasurementValue/EDXBABLE/ORIGINATOR/VALUE">
														<MeasurementValue>
															<xsl:value-of select="."/>
														</MeasurementValue>
													</xsl:for-each>
													<xsl:for-each select="MeasurementRange">
														<MeasurementRange>
															<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																<MinimumValue>
																	<xsl:value-of select="."/>
																</MinimumValue>
															</xsl:for-each>
															<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																<MaximumValue>
																	<xsl:value-of select="."/>
																</MaximumValue>
															</xsl:for-each>
														</MeasurementRange>
													</xsl:for-each>
													<xsl:for-each select="UnitOfMeasurement">
														<UnitOfMeasurement>
															<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																<UOMCoded>
																	<xsl:value-of select="."/>
																</UOMCoded>
															</xsl:for-each>
														</UnitOfMeasurement>
													</xsl:for-each>
												</Measurement>
											</xsl:for-each>
										</TotalNetNetWeight>
									</xsl:for-each>
									<xsl:for-each select="TotalTareWeight">
										<TotalTareWeight>
											<xsl:for-each select="Measurement">
												<Measurement>
													<xsl:for-each select="MeasurementValue/EDXBABLE/ORIGINATOR/VALUE">
														<MeasurementValue>
															<xsl:value-of select="."/>
														</MeasurementValue>
													</xsl:for-each>
													<xsl:for-each select="MeasurementRange">
														<MeasurementRange>
															<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																<MinimumValue>
																	<xsl:value-of select="."/>
																</MinimumValue>
															</xsl:for-each>
															<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																<MaximumValue>
																	<xsl:value-of select="."/>
																</MaximumValue>
															</xsl:for-each>
														</MeasurementRange>
													</xsl:for-each>
													<xsl:for-each select="UnitOfMeasurement">
														<UnitOfMeasurement>
															<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																<UOMCoded>
																	<xsl:value-of select="."/>
																</UOMCoded>
															</xsl:for-each>
														</UnitOfMeasurement>
													</xsl:for-each>
												</Measurement>
											</xsl:for-each>
										</TotalTareWeight>
									</xsl:for-each>
									<xsl:for-each select="GrossVolume">
										<GrossVolume>
											<xsl:for-each select="Measurement">
												<Measurement>
													<xsl:for-each select="MeasurementValue/EDXBABLE/ORIGINATOR/VALUE">
														<MeasurementValue>
															<xsl:value-of select="."/>
														</MeasurementValue>
													</xsl:for-each>
													<xsl:for-each select="MeasurementRange">
														<MeasurementRange>
															<xsl:for-each select="MinimumValue/EDXBABLE/ORIGINATOR/VALUE">
																<MinimumValue>
																	<xsl:value-of select="."/>
																</MinimumValue>
															</xsl:for-each>
															<xsl:for-each select="MaximumValue/EDXBABLE/ORIGINATOR/VALUE">
																<MaximumValue>
																	<xsl:value-of select="."/>
																</MaximumValue>
															</xsl:for-each>
														</MeasurementRange>
													</xsl:for-each>
													<xsl:for-each select="UnitOfMeasurement">
														<UnitOfMeasurement>
															<xsl:for-each select="UOMCoded/EDXBABLE/ORIGINATOR/VALUE">
																<UOMCoded>
																	<xsl:value-of select="."/>
																</UOMCoded>
															</xsl:for-each>
														</UnitOfMeasurement>
													</xsl:for-each>
												</Measurement>
											</xsl:for-each>
										</GrossVolume>
									</xsl:for-each>
								</TransportPackagingTotals>
							</xsl:for-each>
							<xsl:for-each select="SummaryNote/EDXBABLE/DESTINATION/RESOLVEDVALUE">
								<SummaryNote>
									<xsl:value-of select="."/>
								</SummaryNote>
							</xsl:for-each>
						</OrderSummary>
					</xsl:for-each>
				</OriginalOrderSummary>
				<xsl:for-each select="RevisedOrderSummary"/>
			</OrderResponseSummary>
		</OrderResponse>
	</xsl:template>
</xsl:stylesheet><!-- Stylus Studio meta-information - (c)1998-2002 eXcelon Corp.
<metaInformation>
<scenarios ><scenario default="yes" name="standar MAp from Order to order response" userelativepaths="yes" externalpreview="no" url="POAXIS2MSA_S00424_20021107071122&#x2D;1.xml" htmlbaseurl="" processortype="xalan" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/><scenario default="no" name="Scenario1" userelativepaths="yes" externalpreview="no" url="POAXIS2MSA_S00423_20021015101001&#x2D;1.xml" htmlbaseurl="" processortype="internal" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/></scenarios><MapperInfo srcSchemaPath="FullOrderSchemaBableised_v2.xsd" srcSchemaRoot="EDXDATA" srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="OrderResponse.xsd" destSchemaRoot="OrderResponse" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
</metaInformation>
-->