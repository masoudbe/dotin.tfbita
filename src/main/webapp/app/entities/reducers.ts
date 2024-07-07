import additionalBrokerInformation from 'app/entities/additional-broker-information/additional-broker-information.reducer';
import advisorDefinition from 'app/entities/advisor-definition/advisor-definition.reducer';
import advisorDefinitionDeposit from 'app/entities/advisor-definition-deposit/advisor-definition-deposit.reducer';
import attribute from 'app/entities/attribute/attribute.reducer';
import attributeValue from 'app/entities/attribute-value/attribute-value.reducer';
import attributeValueGroup from 'app/entities/attribute-value-group/attribute-value-group.reducer';
import auditCompanyInfo from 'app/entities/audit-company-info/audit-company-info.reducer';
import basicInfo from 'app/entities/basic-info/basic-info.reducer';
import category from 'app/entities/category/category.reducer';
import categoryElement from 'app/entities/category-element/category-element.reducer';
import creditTypeCondition from 'app/entities/credit-type-condition/credit-type-condition.reducer';
import creditTypeConditionInfo from 'app/entities/credit-type-condition-info/credit-type-condition-info.reducer';
import currencyExchangeInfo from 'app/entities/currency-exchange-info/currency-exchange-info.reducer';
import custom from 'app/entities/custom/custom.reducer';
import customJustification from 'app/entities/custom-justification/custom-justification.reducer';
import customJustificationChild from 'app/entities/custom-justification-child/custom-justification-child.reducer';
import documentTransaction from 'app/entities/document-transaction/document-transaction.reducer';
import draft from 'app/entities/draft/draft.reducer';
import draftAccountInfo from 'app/entities/draft-account-info/draft-account-info.reducer';
import draftCertificateType from 'app/entities/draft-certificate-type/draft-certificate-type.reducer';
import draftCustomJustification from 'app/entities/draft-custom-justification/draft-custom-justification.reducer';
import draftDocumentTransactionContainer from 'app/entities/draft-document-transaction-container/draft-document-transaction-container.reducer';
import draftExtend from 'app/entities/draft-extend/draft-extend.reducer';
import draftFactor from 'app/entities/draft-factor/draft-factor.reducer';
import draftProductInfo from 'app/entities/draft-product-info/draft-product-info.reducer';
import draftReceipt from 'app/entities/draft-receipt/draft-receipt.reducer';
import draftReceiptDocumentTransactionContainer from 'app/entities/draft-receipt-document-transaction-container/draft-receipt-document-transaction-container.reducer';
import draftRequestType from 'app/entities/draft-request-type/draft-request-type.reducer';
import draftStatusInfo from 'app/entities/draft-status-info/draft-status-info.reducer';
import draftTax from 'app/entities/draft-tax/draft-tax.reducer';
import draftType from 'app/entities/draft-type/draft-type.reducer';
import draftTypeAccountInfo from 'app/entities/draft-type-account-info/draft-type-account-info.reducer';
import draftTypeTopicInfo from 'app/entities/draft-type-topic-info/draft-type-topic-info.reducer';
import draftUsedAssurance from 'app/entities/draft-used-assurance/draft-used-assurance.reducer';
import insuranceCompanyInfo from 'app/entities/insurance-company-info/insurance-company-info.reducer';
import justificationDeductionAmount from 'app/entities/justification-deduction-amount/justification-deduction-amount.reducer';
import justificationDeductionAmountPart from 'app/entities/justification-deduction-amount-part/justification-deduction-amount-part.reducer';
import justificationDeductionDetail from 'app/entities/justification-deduction-detail/justification-deduction-detail.reducer';
import licenceInfo from 'app/entities/licence-info/licence-info.reducer';
import longValue from 'app/entities/long-value/long-value.reducer';
import objectiveCategory from 'app/entities/objective-category/objective-category.reducer';
import objectiveCategoryElement from 'app/entities/objective-category-element/objective-category-element.reducer';
import orderRegistrationInfo from 'app/entities/order-registration-info/order-registration-info.reducer';
import orderRegService from 'app/entities/order-reg-service/order-reg-service.reducer';
import paymentCondition from 'app/entities/payment-condition/payment-condition.reducer';
import paymentCurrencyRateType from 'app/entities/payment-currency-rate-type/payment-currency-rate-type.reducer';
import paymentItemType from 'app/entities/payment-item-type/payment-item-type.reducer';
import product from 'app/entities/product/product.reducer';
import productType from 'app/entities/product-type/product-type.reducer';
import purchaseFromOtherResources from 'app/entities/purchase-from-other-resources/purchase-from-other-resources.reducer';
import serviceTariff from 'app/entities/service-tariff/service-tariff.reducer';
import stringValue from 'app/entities/string-value/string-value.reducer';
import suggestedSanctionInfo from 'app/entities/suggested-sanction-info/suggested-sanction-info.reducer';
import swiftBic from 'app/entities/swift-bic/swift-bic.reducer';
import tradeTypeCode from 'app/entities/trade-type-code/trade-type-code.reducer';
import transferMethodManagement from 'app/entities/transfer-method-management/transfer-method-management.reducer';
import transportationType from 'app/entities/transportation-type/transportation-type.reducer';
import typeAttribute from 'app/entities/type-attribute/type-attribute.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  additionalBrokerInformation,
  advisorDefinition,
  advisorDefinitionDeposit,
  attribute,
  attributeValue,
  attributeValueGroup,
  auditCompanyInfo,
  basicInfo,
  category,
  categoryElement,
  creditTypeCondition,
  creditTypeConditionInfo,
  currencyExchangeInfo,
  custom,
  customJustification,
  customJustificationChild,
  documentTransaction,
  draft,
  draftAccountInfo,
  draftCertificateType,
  draftCustomJustification,
  draftDocumentTransactionContainer,
  draftExtend,
  draftFactor,
  draftProductInfo,
  draftReceipt,
  draftReceiptDocumentTransactionContainer,
  draftRequestType,
  draftStatusInfo,
  draftTax,
  draftType,
  draftTypeAccountInfo,
  draftTypeTopicInfo,
  draftUsedAssurance,
  insuranceCompanyInfo,
  justificationDeductionAmount,
  justificationDeductionAmountPart,
  justificationDeductionDetail,
  licenceInfo,
  longValue,
  objectiveCategory,
  objectiveCategoryElement,
  orderRegistrationInfo,
  orderRegService,
  paymentCondition,
  paymentCurrencyRateType,
  paymentItemType,
  product,
  productType,
  purchaseFromOtherResources,
  serviceTariff,
  stringValue,
  suggestedSanctionInfo,
  swiftBic,
  tradeTypeCode,
  transferMethodManagement,
  transportationType,
  typeAttribute,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
