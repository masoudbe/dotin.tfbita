import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AdditionalBrokerInformation from './additional-broker-information';
import AdvisorDefinition from './advisor-definition';
import AdvisorDefinitionDeposit from './advisor-definition-deposit';
import Attribute from './attribute';
import AttributeValue from './attribute-value';
import AttributeValueGroup from './attribute-value-group';
import AuditCompanyInfo from './audit-company-info';
import BasicInfo from './basic-info';
import Category from './category';
import CategoryElement from './category-element';
import CreditTypeCondition from './credit-type-condition';
import CreditTypeConditionInfo from './credit-type-condition-info';
import CurrencyExchangeInfo from './currency-exchange-info';
import Custom from './custom';
import CustomJustification from './custom-justification';
import CustomJustificationChild from './custom-justification-child';
import DocumentTransaction from './document-transaction';
import Draft from './draft';
import DraftAccountInfo from './draft-account-info';
import DraftCertificateType from './draft-certificate-type';
import DraftCustomJustification from './draft-custom-justification';
import DraftDocumentTransactionContainer from './draft-document-transaction-container';
import DraftExtend from './draft-extend';
import DraftFactor from './draft-factor';
import DraftProductInfo from './draft-product-info';
import DraftReceipt from './draft-receipt';
import DraftReceiptDocumentTransactionContainer from './draft-receipt-document-transaction-container';
import DraftRequestType from './draft-request-type';
import DraftStatusInfo from './draft-status-info';
import DraftTax from './draft-tax';
import DraftType from './draft-type';
import DraftTypeAccountInfo from './draft-type-account-info';
import DraftTypeTopicInfo from './draft-type-topic-info';
import DraftUsedAssurance from './draft-used-assurance';
import InsuranceCompanyInfo from './insurance-company-info';
import JustificationDeductionAmount from './justification-deduction-amount';
import JustificationDeductionAmountPart from './justification-deduction-amount-part';
import JustificationDeductionDetail from './justification-deduction-detail';
import LicenceInfo from './licence-info';
import LongValue from './long-value';
import ObjectiveCategory from './objective-category';
import ObjectiveCategoryElement from './objective-category-element';
import OrderRegistrationInfo from './order-registration-info';
import OrderRegService from './order-reg-service';
import PaymentCondition from './payment-condition';
import PaymentCurrencyRateType from './payment-currency-rate-type';
import PaymentItemType from './payment-item-type';
import Product from './product';
import ProductType from './product-type';
import PurchaseFromOtherResources from './purchase-from-other-resources';
import ServiceTariff from './service-tariff';
import StringValue from './string-value';
import SuggestedSanctionInfo from './suggested-sanction-info';
import SwiftBic from './swift-bic';
import TradeTypeCode from './trade-type-code';
import TransferMethodManagement from './transfer-method-management';
import TransportationType from './transportation-type';
import TypeAttribute from './type-attribute';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="additional-broker-information/*" element={<AdditionalBrokerInformation />} />
        <Route path="advisor-definition/*" element={<AdvisorDefinition />} />
        <Route path="advisor-definition-deposit/*" element={<AdvisorDefinitionDeposit />} />
        <Route path="attribute/*" element={<Attribute />} />
        <Route path="attribute-value/*" element={<AttributeValue />} />
        <Route path="attribute-value-group/*" element={<AttributeValueGroup />} />
        <Route path="audit-company-info/*" element={<AuditCompanyInfo />} />
        <Route path="basic-info/*" element={<BasicInfo />} />
        <Route path="category/*" element={<Category />} />
        <Route path="category-element/*" element={<CategoryElement />} />
        <Route path="credit-type-condition/*" element={<CreditTypeCondition />} />
        <Route path="credit-type-condition-info/*" element={<CreditTypeConditionInfo />} />
        <Route path="currency-exchange-info/*" element={<CurrencyExchangeInfo />} />
        <Route path="custom/*" element={<Custom />} />
        <Route path="custom-justification/*" element={<CustomJustification />} />
        <Route path="custom-justification-child/*" element={<CustomJustificationChild />} />
        <Route path="document-transaction/*" element={<DocumentTransaction />} />
        <Route path="draft/*" element={<Draft />} />
        <Route path="draft-account-info/*" element={<DraftAccountInfo />} />
        <Route path="draft-certificate-type/*" element={<DraftCertificateType />} />
        <Route path="draft-custom-justification/*" element={<DraftCustomJustification />} />
        <Route path="draft-document-transaction-container/*" element={<DraftDocumentTransactionContainer />} />
        <Route path="draft-extend/*" element={<DraftExtend />} />
        <Route path="draft-factor/*" element={<DraftFactor />} />
        <Route path="draft-product-info/*" element={<DraftProductInfo />} />
        <Route path="draft-receipt/*" element={<DraftReceipt />} />
        <Route path="draft-receipt-document-transaction-container/*" element={<DraftReceiptDocumentTransactionContainer />} />
        <Route path="draft-request-type/*" element={<DraftRequestType />} />
        <Route path="draft-status-info/*" element={<DraftStatusInfo />} />
        <Route path="draft-tax/*" element={<DraftTax />} />
        <Route path="draft-type/*" element={<DraftType />} />
        <Route path="draft-type-account-info/*" element={<DraftTypeAccountInfo />} />
        <Route path="draft-type-topic-info/*" element={<DraftTypeTopicInfo />} />
        <Route path="draft-used-assurance/*" element={<DraftUsedAssurance />} />
        <Route path="insurance-company-info/*" element={<InsuranceCompanyInfo />} />
        <Route path="justification-deduction-amount/*" element={<JustificationDeductionAmount />} />
        <Route path="justification-deduction-amount-part/*" element={<JustificationDeductionAmountPart />} />
        <Route path="justification-deduction-detail/*" element={<JustificationDeductionDetail />} />
        <Route path="licence-info/*" element={<LicenceInfo />} />
        <Route path="long-value/*" element={<LongValue />} />
        <Route path="objective-category/*" element={<ObjectiveCategory />} />
        <Route path="objective-category-element/*" element={<ObjectiveCategoryElement />} />
        <Route path="order-registration-info/*" element={<OrderRegistrationInfo />} />
        <Route path="order-reg-service/*" element={<OrderRegService />} />
        <Route path="payment-condition/*" element={<PaymentCondition />} />
        <Route path="payment-currency-rate-type/*" element={<PaymentCurrencyRateType />} />
        <Route path="payment-item-type/*" element={<PaymentItemType />} />
        <Route path="product/*" element={<Product />} />
        <Route path="product-type/*" element={<ProductType />} />
        <Route path="purchase-from-other-resources/*" element={<PurchaseFromOtherResources />} />
        <Route path="service-tariff/*" element={<ServiceTariff />} />
        <Route path="string-value/*" element={<StringValue />} />
        <Route path="suggested-sanction-info/*" element={<SuggestedSanctionInfo />} />
        <Route path="swift-bic/*" element={<SwiftBic />} />
        <Route path="trade-type-code/*" element={<TradeTypeCode />} />
        <Route path="transfer-method-management/*" element={<TransferMethodManagement />} />
        <Route path="transportation-type/*" element={<TransportationType />} />
        <Route path="type-attribute/*" element={<TypeAttribute />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
