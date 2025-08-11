<?xml version="1.0" encoding="UTF-8"?>
<!--Version 1 change Order to Change order response-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<OrderResponse xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="OrderResponse.xsd" xsi:schemaLocation="OrderResponse.xsd file://c:\EDX\SRC\TradeRoute\transformations\MincomMSA\mappings\OrderResponse.xsd">
      <!--OrderResponse xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="OrderResponse.xsd" xsi:schemaLocation="OrderResponse.xsd file://c:\Dynex\Xsd\xCBL30\singleRoot\OrderResponse.xsd"-->
			<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderHeader">
				<OrderResponseHeader>
					<xsl:for-each select="ChangeOrderNumber">
						<OrderResponseNumber>
							<xsl:for-each select="BuyerChangeOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
								<BuyerOrderResponseNumber>
									<xsl:value-of select="."/>
								</BuyerOrderResponseNumber>
							</xsl:for-each>
							<SellerOrderResponseNumber></SellerOrderResponseNumber>
							<xsl:for-each select="ListOfMessageID">
								<ListOfMessageID>
									<xsl:for-each select="MessageID">
										<MessageID>
											<xsl:for-each select="IDNumber/EDXBABLE/ORIGINATOR/VALUE">
												<IDNumber>
													<xsl:value-of select="."/>
												</IDNumber>
											</xsl:for-each>
											<xsl:for-each select="IDAssignedBy">
												<IDAssignedBy>
													<xsl:for-each select="IDAssignedByCoded/EDXBABLE/ORIGINATOR/VALUE">
														<IDAssignedByCoded>
															<xsl:value-of select="."/>
														</IDAssignedByCoded>
													</xsl:for-each>
													<xsl:for-each select="IDAssignedByCodedOther/EDXBABLE/ORIGINATOR/VALUE">
														<IDAssignedByCodedOther>
															<xsl:value-of select="."/>
														</IDAssignedByCodedOther>
													</xsl:for-each>
												</IDAssignedBy>
											</xsl:for-each>
										</MessageID>
									</xsl:for-each>
								</ListOfMessageID>
							</xsl:for-each>
						</OrderResponseNumber>
					</xsl:for-each>
					<OrderResponseIssueDate>20020801T00:00:00+10:00</OrderResponseIssueDate>
					<OrderResponseDocTypeCoded>ChangeOrderResponse</OrderResponseDocTypeCoded>
					<xsl:for-each select="OrderReference">
						<OrderReference>
							<xsl:for-each select="Reference">
								<Reference>
									<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
										<RefNum>
											<xsl:value-of select="."/>
										</RefNum>
									</xsl:for-each>
								</Reference>
							</xsl:for-each>
						</OrderReference>
					</xsl:for-each>
					<ChangeOrderReference>
						<Reference>
							<xsl:for-each select="ChangeOrderNumber/BuyerChangeOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
								<RefNum>
									<xsl:value-of select="."/>
								</RefNum>
							</xsl:for-each>
						</Reference>
					</ChangeOrderReference>
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
											<xsl:for-each select="POBox/EDXBABLE/ORIGINATOR/NAME">
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
													<TimezoneCoded/>
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
								</Party>
							</xsl:for-each>
						</SellerParty>
					</xsl:for-each>
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
																<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																	<ContactName>
																		<xsl:value-of select="."/>
																	</ContactName>
																</xsl:for-each>
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
												</ListOfContact>
											</xsl:for-each>
										</OtherContacts>
									</xsl:for-each>
								</Party>
							</xsl:for-each>
						</BuyerParty>
					</xsl:for-each>
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
												</Reference>
											</xsl:for-each>
										</SupportingReference>
									</xsl:for-each>
									<xsl:for-each select="SupportingSubReference">
										<SupportingSubReference>
											<xsl:for-each select="Reference">
												<Reference>
													<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
														<RefNum>
															<xsl:value-of select="."/>
														</RefNum>
													</xsl:for-each>
												</Reference>
											</xsl:for-each>
										</SupportingSubReference>
									</xsl:for-each>
									<xsl:for-each select="ReferenceDescription/EDXBABLE/ORIGINATOR/VALUE">
										<ReferenceDescription>
											<xsl:value-of select="."/>
										</ReferenceDescription>
									</xsl:for-each>
								</ReferenceCoded>
							</xsl:for-each>
						</ListOfReferenceCoded>
					</xsl:for-each>
					<xsl:for-each select="Purpose">
						<Purpose>
							<PurposeCoded>Original</PurposeCoded>
						</Purpose>
					</xsl:for-each>
					<ResponseType>
						<ResponseTypeCoded>Accepted</ResponseTypeCoded>
					</ResponseType>
					<xsl:for-each select="OriginalOrderHeader">
						<OriginalOrderHeader>
							<xsl:for-each select="OrderHeader">
								<OrderHeader>
									<xsl:for-each select="OrderNumber">
										<OrderNumber>
											<xsl:for-each select="BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
												<BuyerOrderNumber>
													<xsl:value-of select="."/>
												</BuyerOrderNumber>
											</xsl:for-each>
											<xsl:for-each select="SellerOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
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
									<xsl:for-each select="ReleaseNumber/EDXBABLE/ORIGINATOR/VALUE">
										<ReleaseNumber>
											<xsl:value-of select="."/>
										</ReleaseNumber>
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
														</Party>
													</xsl:for-each>
												</BuyerParty>
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
														</Party>
													</xsl:for-each>
												</RemitToParty>
											</xsl:for-each>
										</OrderParty>
									</xsl:for-each>
									<xsl:for-each select="OrderDates">
										<OrderDates>
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
										</OrderDates>
									</xsl:for-each>
									<xsl:for-each select="OrderHeaderAttachments">
										<OrderHeaderAttachments>
											<xsl:for-each select="ListOfAttachment">
												<ListOfAttachment>
													<xsl:for-each select="Attachment">
														<Attachment>
															<xsl:for-each select="AttachmentPurpose/EDXBABLE/ORIGINATOR/VALUE">
																<AttachmentPurpose>
																	<xsl:value-of select="."/>
																</AttachmentPurpose>
															</xsl:for-each>
															<xsl:for-each select="FileName/EDXBABLE/ORIGINATOR/VALUE">
																<FileName>
																	<xsl:value-of select="."/>
																</FileName>
															</xsl:for-each>
															<xsl:for-each select="AttachmentTitle/EDXBABLE/ORIGINATOR/VALUE">
																<AttachmentTitle>
																	<xsl:value-of select="."/>
																</AttachmentTitle>
															</xsl:for-each>
															<xsl:for-each select="MIMEType/EDXBABLE/ORIGINATOR/VALUE">
																<MIMEType>
																	<xsl:value-of select="."/>
																</MIMEType>
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
										</OrderHeaderAttachments>
									</xsl:for-each>
								</OrderHeader>
							</xsl:for-each>
						</OriginalOrderHeader>
					</xsl:for-each>
					<xsl:for-each select=".">
						<ChangeOrderHeader>
							<xsl:for-each select="ChangeOrderNumber">
								<ChangeOrderNumber>
									<xsl:for-each select="BuyerChangeOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
										<BuyerChangeOrderNumber>
											<xsl:value-of select="."/>
										</BuyerChangeOrderNumber>
									</xsl:for-each>
									<SellerChangeOrderNumber></SellerChangeOrderNumber>
									<xsl:for-each select="ListOfMessageID">
										<ListOfMessageID>
											<xsl:for-each select="MessageID">
												<MessageID>
													<xsl:for-each select="IDNumber/EDXBABLE/ORIGINATOR/VALUE">
														<IDNumber>
															<xsl:value-of select="."/>
														</IDNumber>
													</xsl:for-each>
													<xsl:for-each select="IDAssignedBy">
														<IDAssignedBy>
															<xsl:for-each select="IDAssignedByCoded/EDXBABLE/ORIGINATOR/VALUE">
																<IDAssignedByCoded>
																	<xsl:value-of select="."/>
																</IDAssignedByCoded>
															</xsl:for-each>
															<xsl:for-each select="IDAssignedByCodedOther/EDXBABLE/ORIGINATOR/VALUE">
																<IDAssignedByCodedOther>
																	<xsl:value-of select="."/>
																</IDAssignedByCodedOther>
															</xsl:for-each>
														</IDAssignedBy>
													</xsl:for-each>
												</MessageID>
											</xsl:for-each>
										</ListOfMessageID>
									</xsl:for-each>
								</ChangeOrderNumber>
							</xsl:for-each>
							<xsl:for-each select="ChangeOrderSequence/EDXBABLE/ORIGINATOR/VALUE">
								<ChangeOrderSequence>
									<xsl:value-of select="."/>
								</ChangeOrderSequence>
							</xsl:for-each>
							<xsl:for-each select="ChangeOrderIssueDate/EDXBABLE/ORIGINATOR/VALUE">
								<ChangeOrderIssueDate>
									<xsl:value-of select="."/>
								</ChangeOrderIssueDate>
							</xsl:for-each>
							<xsl:for-each select="OrderReference">
								<OrderReference>
									<xsl:for-each select="Reference">
										<Reference>
											<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
												<RefNum>
													<xsl:value-of select="."/>
												</RefNum>
											</xsl:for-each>
										</Reference>
									</xsl:for-each>
								</OrderReference>
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
															<xsl:for-each select="ContactDescription/EDXBABLE/ORIGINATOR/VALUE">
																<ContactDescription>
																	<xsl:value-of select="."/>
																</ContactDescription>
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
														</Reference>
													</xsl:for-each>
												</SupportingReference>
											</xsl:for-each>
											<xsl:for-each select="SupportingSubReference">
												<SupportingSubReference>
													<xsl:for-each select="Reference">
														<Reference>
															<xsl:for-each select="RefNum/EDXBABLE/ORIGINATOR/VALUE">
																<RefNum>
																	<xsl:value-of select="."/>
																</RefNum>
															</xsl:for-each>
														</Reference>
													</xsl:for-each>
												</SupportingSubReference>
											</xsl:for-each>
											<xsl:for-each select="ReferenceDescription/EDXBABLE/ORIGINATOR/VALUE">
												<ReferenceDescription>
													<xsl:value-of select="."/>
												</ReferenceDescription>
											</xsl:for-each>
										</ReferenceCoded>
									</xsl:for-each>
								</ListOfReferenceCoded>
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
							<xsl:for-each select="ChangeType">
								<ChangeType>
									<xsl:for-each select="ChangeTypeCoded/EDXBABLE/ORIGINATOR/VALUE">
										<ChangeTypeCoded>
											<xsl:value-of select="."/>
										</ChangeTypeCoded>
									</xsl:for-each>
								</ChangeType>
							</xsl:for-each>
							<xsl:for-each select="OrderHeaderChanges">
								<OrderHeaderChanges>
									<xsl:for-each select="OrderHeader">
										<OrderHeader>
											<xsl:for-each select="OrderNumber">
												<OrderNumber>
													<xsl:for-each select="BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
														<BuyerOrderNumber>
															<xsl:value-of select="."/>
														</BuyerOrderNumber>
													</xsl:for-each>
													<xsl:for-each select="SellerOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
														<SellerOrderNumber>
															<xsl:value-of select="."/>
														</SellerOrderNumber>
													</xsl:for-each>
													<xsl:for-each select="ListOfMessageID">
														<ListOfMessageID>
															<xsl:for-each select="MessageID">
																<MessageID>
																	<xsl:for-each select="IDNumber/EDXBABLE/ORIGINATOR/VALUE">
																		<IDNumber>
																			<xsl:value-of select="."/>
																		</IDNumber>
																	</xsl:for-each>
																	<xsl:for-each select="IDAssignedBy">
																		<IDAssignedBy>
																			<xsl:for-each select="IDAssignedByCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<IDAssignedByCoded>
																					<xsl:value-of select="."/>
																				</IDAssignedByCoded>
																			</xsl:for-each>
																		</IDAssignedBy>
																	</xsl:for-each>
																</MessageID>
															</xsl:for-each>
														</ListOfMessageID>
													</xsl:for-each>
												</OrderNumber>
											</xsl:for-each>
											<xsl:for-each select="OrderIssueDate/EDXBABLE/ORIGINATOR/VALUE">
												<OrderIssueDate>
													<xsl:value-of select="."/>
												</OrderIssueDate>
											</xsl:for-each>
											<xsl:for-each select="OrderReferences"/>
											<xsl:for-each select="Purpose">
												<Purpose>
													<xsl:for-each select="PurposeCoded/EDXBABLE/ORIGINATOR/VALUE">
														<PurposeCoded>
															<xsl:value-of select="."/>
														</PurposeCoded>
													</xsl:for-each>
												</Purpose>
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
																</Party>
															</xsl:for-each>
														</BuyerParty>
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
																</Party>
															</xsl:for-each>
														</SellerParty>
													</xsl:for-each>
												</OrderParty>
											</xsl:for-each>
										</OrderHeader>
									</xsl:for-each>
								</OrderHeaderChanges>
							</xsl:for-each>
							<xsl:for-each select="ChangeOrderHeaderNote/EDXBABLE/ORIGINATOR/VALUE">
								<ChangeOrderHeaderNote>
									<xsl:value-of select="."/>
								</ChangeOrderHeaderNote>
							</xsl:for-each>
						</ChangeOrderHeader>
					</xsl:for-each>
					<xsl:for-each select="OrderHeaderChanges">
						<OrderHeaderChanges>
							<xsl:for-each select="OrderHeader">
								<OrderHeader>
									<xsl:for-each select="OrderNumber">
										<OrderNumber>
											<xsl:for-each select="BuyerOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
												<BuyerOrderNumber>
													<xsl:value-of select="."/>
												</BuyerOrderNumber>
											</xsl:for-each>
											<xsl:for-each select="SellerOrderNumber/EDXBABLE/ORIGINATOR/VALUE">
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
									<xsl:for-each select="OrderCurrency">
										<OrderCurrency>
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
														</Party>
													</xsl:for-each>
												</BuyerParty>
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
																</NameAddress>
															</xsl:for-each>
															<xsl:for-each select="OrderContact">
																<OrderContact>
																	<xsl:for-each select="Contact">
																		<Contact>
																			<xsl:for-each select="ContactName/EDXBABLE/ORIGINATOR/VALUE">
																				<ContactName>
																					<xsl:value-of select="."/>
																				</ContactName>
																			</xsl:for-each>
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
												</RemitToParty>
											</xsl:for-each>
										</OrderParty>
									</xsl:for-each>
									<xsl:for-each select="OrderDates">
										<OrderDates>
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
										</OrderDates>
									</xsl:for-each>
									<xsl:for-each select="ListOfTransport">
										<ListOfTransport>
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
														</CarrierID>
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
										</ListOfTransport>
									</xsl:for-each>
									<xsl:for-each select="OrderHeaderAttachments">
										<OrderHeaderAttachments>
											<xsl:for-each select="ListOfAttachment">
												<ListOfAttachment>
													<xsl:for-each select="Attachment">
														<Attachment>
															<xsl:for-each select="AttachmentPurpose/EDXBABLE/ORIGINATOR/VALUE">
																<AttachmentPurpose>
																	<xsl:value-of select="."/>
																</AttachmentPurpose>
															</xsl:for-each>
															<xsl:for-each select="FileName/EDXBABLE/ORIGINATOR/VALUE">
																<FileName>
																	<xsl:value-of select="."/>
																</FileName>
															</xsl:for-each>
															<xsl:for-each select="ReplacementFile/EDXBABLE/ORIGINATOR/VALUE">
																<ReplacementFile>
																	<xsl:value-of select="."/>
																</ReplacementFile>
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
										</OrderHeaderAttachments>
									</xsl:for-each>
								</OrderHeader>
							</xsl:for-each>
						</OrderHeaderChanges>
					</xsl:for-each>
					<xsl:for-each select="ChangeOrderHeaderNote/EDXBABLE/DESTINATION/RESOLVEDVALUE">
						<OrderResponseHeaderNote>
							<xsl:value-of select="."/>
						</OrderResponseHeaderNote>
					</xsl:for-each>
				</OrderResponseHeader>
			</xsl:for-each>
			<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderDetail">
				<OrderResponseDetail>
					<xsl:for-each select="ListOfChangeOrderItemDetail">
						<ListOfOrderResponseItemDetail>
							<xsl:for-each select="ChangeOrderItemDetail">
								<OrderResponseItemDetail>
									<ItemDetailResponseCoded>ItemAccepted</ItemDetailResponseCoded>
									<xsl:for-each select="OriginalItemDetail">
										<OriginalItemDetail>
											<xsl:for-each select="ItemDetail">
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
																							<xsl:for-each select="ProductIdentifier/EDXBABLE/ORIGINATOR/VALUE">
																								<ProductIdentifier>
																									<xsl:value-of select="."/>
																								</ProductIdentifier>
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
																									<xsl:for-each select="ProductIdentifier/EDXBABLE/ORIGINATOR/VALUE">
																										<ProductIdentifier>
																											<xsl:value-of select="."/>
																										</ProductIdentifier>
																									</xsl:for-each>
																								</ProductIdentifierCoded>
																							</xsl:for-each>
																						</ListOfProductIdentifierCoded>
																					</xsl:for-each>
																				</SubstitutePartNumbers>
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
															<xsl:for-each select="OffCatalogFlag/EDXBABLE/ORIGINATOR/VALUE">
																<OffCatalogFlag>
																	<xsl:value-of select="."/>
																</OffCatalogFlag>
															</xsl:for-each>
															<xsl:for-each select="CountryOfOrigin">
																<CountryOfOrigin>
																	<xsl:for-each select="Country">
																		<Country>
																			<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<CountryCoded>
																					<xsl:value-of select="."/>
																				</CountryCoded>
																			</xsl:for-each>
																		</Country>
																	</xsl:for-each>
																</CountryOfOrigin>
															</xsl:for-each>
															<xsl:for-each select="CountryOfDestination">
																<CountryOfDestination>
																	<xsl:for-each select="Country">
																		<Country>
																			<xsl:for-each select="CountryCoded/EDXBABLE/ORIGINATOR/VALUE">
																				<CountryCoded>
																					<xsl:value-of select="."/>
																				</CountryCoded>
																			</xsl:for-each>
																		</Country>
																	</xsl:for-each>
																</CountryOfDestination>
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
																						</UnitOfMeasurement>
																					</xsl:for-each>
																				</UnitPrice>
																			</xsl:for-each>
																			<xsl:for-each select="PriceBasisQuantity">
																				<PriceBasisQuantity>
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
																				</PriceBasisQuantity>
																			</xsl:for-each>
																			<xsl:for-each select="PriceQuantityRange">
																				<PriceQuantityRange>
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
																				</PriceQuantityRange>
																			</xsl:for-each>
																			<xsl:for-each select="PriceMultiplier">
																				<PriceMultiplier>
																					<xsl:for-each select="PriceMultiplierCoded/EDXBABLE/ORIGINATOR/VALUE">
																						<PriceMultiplierCoded>
																							<xsl:value-of select="."/>
																						</PriceMultiplierCoded>
																					</xsl:for-each>
																					<xsl:for-each select="Multiplier/EDXBABLE/ORIGINATOR/VALUE">
																						<Multiplier>
																							<xsl:value-of select="."/>
																						</Multiplier>
																					</xsl:for-each>
																				</PriceMultiplier>
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
																	<xsl:for-each select="TaxPercent/EDXBABLE/ORIGINATOR/VALUE">
																		<TaxPercent>
																			<xsl:value-of select="."/>
																		</TaxPercent>
																	</xsl:for-each>
																	<xsl:for-each select="TaxableAmount/EDXBABLE/ORIGINATOR/VALUE">
																		<TaxableAmount>
																			<xsl:value-of select="."/>
																		</TaxableAmount>
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
																					<xsl:for-each select="TypeOfAllowanceOrCharge">
																						<TypeOfAllowanceOrCharge>
																							<xsl:for-each select="PercentageAllowanceOrCharge">
																								<PercentageAllowanceOrCharge>
																									<xsl:for-each select="PercentQualifier">
																										<PercentQualifier>
																											<xsl:for-each select="PercentQualifierCoded/EDXBABLE/ORIGINATOR/VALUE">
																												<PercentQualifierCoded>
																													<xsl:value-of select="."/>
																												</PercentQualifierCoded>
																											</xsl:for-each>
																										</PercentQualifier>
																									</xsl:for-each>
																									<xsl:for-each select="Percent/EDXBABLE/ORIGINATOR/VALUE">
																										<Percent>
																											<xsl:value-of select="."/>
																										</Percent>
																									</xsl:for-each>
																								</PercentageAllowanceOrCharge>
																							</xsl:for-each>
																						</TypeOfAllowanceOrCharge>
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
																		</MonetaryValue>
																	</xsl:for-each>
																</TotalValue>
															</xsl:for-each>
														</PricingDetail>
													</xsl:for-each>
													<xsl:for-each select="../../ItemDetailChanges/ItemDetail/DeliveryDetail">
														<DeliveryDetail>
															<xsl:for-each select="ListOfScheduleLine">
																<ListOfScheduleLine>
																	<xsl:for-each select="ScheduleLine">
																		<ScheduleLine>
																			<xsl:for-each select="Quantity">
																				<Quantity>
																					<xsl:for-each select="QuantityValue/EDXBABLE/ORIGINATOR/VALUE">
																						<QuantityValue>
																							<xsl:value-of select="."/>
																						</QuantityValue>
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
																			<xsl:for-each select="RequestedDeliveryDate/EDXBABLE/ORIGINATOR/VALUE">
																				<RequestedDeliveryDate>
																					<xsl:value-of select="."/>
																				</RequestedDeliveryDate>
																			</xsl:for-each>
																		</ScheduleLine>
																	</xsl:for-each>
																</ListOfScheduleLine>
															</xsl:for-each>
														</DeliveryDetail>
													</xsl:for-each>
													<xsl:for-each select="../../ItemDetailChanges/ItemDetail/LineItemNote/EDXBABLE/ORIGINATOR/VALUE">
														<LineItemNote>
															<xsl:value-of select="."/>
														</LineItemNote>
													</xsl:for-each>
													<xsl:for-each select="../../ItemDetailChanges/ItemDetail/LineItemAttachments">
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
																			<xsl:for-each select="FileName/EDXBABLE/ORIGINATOR/VALUE">
																				<FileName>
																					<xsl:value-of select="."/>
																				</FileName>
																			</xsl:for-each>
																			<xsl:for-each select="AttachmentTitle/EDXBABLE/ORIGINATOR/VALUE">
																				<AttachmentTitle>
																					<xsl:value-of select="."/>
																				</AttachmentTitle>
																			</xsl:for-each>
																			<xsl:for-each select="AttachmentDescription/EDXBABLE/ORIGINATOR/VALUE">
																				<AttachmentDescription>
																					<xsl:value-of select="."/>
																				</AttachmentDescription>
																			</xsl:for-each>
																			<xsl:for-each select="MIMEType/EDXBABLE/ORIGINATOR/VALUE">
																				<MIMEType>
																					<xsl:value-of select="."/>
																				</MIMEType>
																			</xsl:for-each>
																			<xsl:for-each select="ReplacementFile/EDXBABLE/ORIGINATOR/VALUE">
																				<ReplacementFile>
																					<xsl:value-of select="."/>
																				</ReplacementFile>
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
											</xsl:for-each>
										</OriginalItemDetail>
									</xsl:for-each>
									<xsl:for-each select="ItemDetailChanges">
										<ItemDetailChanges>
											<xsl:for-each select="ItemDetail">
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
																	<xsl:for-each select="SellerLineItemNum/EDXBABLE/ORIGINATOR/VALUE">
																		<SellerLineItemNum>
																			<xsl:value-of select="."/>
																		</SellerLineItemNum>
																	</xsl:for-each>
																</LineItemNum>
															</xsl:for-each>
														</BaseItemDetail>
													</xsl:for-each>
													<xsl:for-each select="../../LineItemNote/EDXBABLE/ORIGINATOR/VALUE">
														<LineItemNote>
															<xsl:value-of select="."/>
														</LineItemNote>
													</xsl:for-each>
												</ItemDetail>
											</xsl:for-each>
										</ItemDetailChanges>
									</xsl:for-each>
								</OrderResponseItemDetail>
							</xsl:for-each>
						</ListOfOrderResponseItemDetail>
					</xsl:for-each>
				</OrderResponseDetail>
			</xsl:for-each>
			<OrderResponseSummary>
				<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderSummary/OriginalOrderSummary">
					<OriginalOrderSummary>
						<xsl:for-each select="OrderSummary">
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
											</MonetaryValue>
										</xsl:for-each>
									</TotalAmount>
								</xsl:for-each>
								<xsl:for-each select="SummaryNote/EDXBABLE/ORIGINATOR/VALUE">
									<SummaryNote>
										<xsl:value-of select="."/>
									</SummaryNote>
								</xsl:for-each>
							</OrderSummary>
						</xsl:for-each>
					</OriginalOrderSummary>
				</xsl:for-each>
				<xsl:for-each select="EDXDATA/DOCDATA/BUSOBJ/ChangeOrder/ChangeOrderSummary/RevisedOrderSummary">
					<RevisedOrderSummary>
						<xsl:for-each select="OrderSummary">
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
											</MonetaryValue>
										</xsl:for-each>
									</TotalAmount>
								</xsl:for-each>
								<xsl:for-each select="SummaryNote/EDXBABLE/ORIGINATOR/VALUE">
									<SummaryNote>
										<xsl:value-of select="."/>
									</SummaryNote>
								</xsl:for-each>
							</OrderSummary>
						</xsl:for-each>
					</RevisedOrderSummary>
				</xsl:for-each>
			</OrderResponseSummary>
		</OrderResponse>
	</xsl:template>
</xsl:stylesheet><!-- Stylus Studio meta-information - (c)1998-2002 eXcelon Corp.
<metaInformation>
<scenarios ><scenario default="yes" name="standar MAp from Order to order response" userelativepaths="yes" externalpreview="no" url="COAXIS2MSA_S0969333_20021014025223&#x2D;5.xml" htmlbaseurl="" processortype="internal" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/><scenario default="no" name="Scenario1" userelativepaths="yes" externalpreview="no" url="POAXIS2MSA_S00423_20021015101001&#x2D;1.xml" htmlbaseurl="" processortype="internal" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext=""/></scenarios><MapperInfo srcSchemaPath="Full_ChangeOrder_Bableised.xsd" srcSchemaRoot="EDXDATA" srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="OrderResponse.xsd" destSchemaRoot="OrderResponse" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
</metaInformation>
-->