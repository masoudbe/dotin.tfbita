import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Category from './category';
import CategoryElement from './category-element';
import Custom from './custom';
import LicenceInfo from './licence-info';
import ServiceTariff from './service-tariff';
import OrderRegService from './order-reg-service';
import ObjectiveCategory from './objective-category';
import ObjectiveCategoryElement from './objective-category-element';
import Product from './product';
import ProductType from './product-type';
import AttributeValue from './attribute-value';
import TypeAttribute from './type-attribute';
import AttributeValueGroup from './attribute-value-group';
import Attribute from './attribute';
import PurchaseFromOtherResources from './purchase-from-other-resources';
import OrderRegistrationInfo from './order-registration-info';
import TransportationType from './transportation-type';
import StringValue from './string-value';
import LongValue from './long-value';
import DraftStatusInfo from './draft-status-info';
import Draft from './draft';
import DraftFactor from './draft-factor';
import DraftUsedAssurance from './draft-used-assurance';
import InsuranceCompanyInfo from './insurance-company-info';
import CurrencyExchangeInfo from './currency-exchange-info';
import SuggestedSanctionInfo from './suggested-sanction-info';
import DraftAccountInfo from './draft-account-info';
import AuditCompanyInfo from './audit-company-info';
import DraftExtend from './draft-extend';
import DraftTax from './draft-tax';
import DocumentTransaction from './document-transaction';
import DraftType from './draft-type';
import DraftTypeTopicInfo from './draft-type-topic-info';
import CreditTypeConditionInfo from './credit-type-condition-info';
import CreditTypeCondition from './credit-type-condition';
import DraftTypeAccountInfo from './draft-type-account-info';
import DraftCertificateType from './draft-certificate-type';
import DraftRequestType from './draft-request-type';
import AdvisorDefinition from './advisor-definition';
import AdvisorDefinitionDeposit from './advisor-definition-deposit';
import TransferMethodManagement from './transfer-method-management';
import SwiftBic from './swift-bic';
import AdditionalBrokerInformation from './additional-broker-information';
import DraftReceipt from './draft-receipt';
import PaymentCurrencyRateType from './payment-currency-rate-type';
import PaymentItemType from './payment-item-type';
import DraftProductInfo from './draft-product-info';
import DraftReceiptDocumentTransactionContainer from './draft-receipt-document-transaction-container';
import DraftDocumentTransactionContainer from './draft-document-transaction-container';
import CustomJustification from './custom-justification';
import TradeTypeCode from './trade-type-code';
import PaymentCondition from './payment-condition';
import CustomJustificationChild from './custom-justification-child';
import JustificationDeductionAmount from './justification-deduction-amount';
import JustificationDeductionAmountPart from './justification-deduction-amount-part';
import JustificationDeductionDetail from './justification-deduction-detail';
import DraftCustomJustification from './draft-custom-justification';
import BasicInfo from './basic-info';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="category/*" element={<Category />} />
        <Route path="category-element/*" element={<CategoryElement />} />
        <Route path="custom/*" element={<Custom />} />
        <Route path="licence-info/*" element={<LicenceInfo />} />
        <Route path="service-tariff/*" element={<ServiceTariff />} />
        <Route path="order-reg-service/*" element={<OrderRegService />} />
        <Route path="objective-category/*" element={<ObjectiveCategory />} />
        <Route path="objective-category-element/*" element={<ObjectiveCategoryElement />} />
        <Route path="product/*" element={<Product />} />
        <Route path="product-type/*" element={<ProductType />} />
        <Route path="attribute-value/*" element={<AttributeValue />} />
        <Route path="type-attribute/*" element={<TypeAttribute />} />
        <Route path="attribute-value-group/*" element={<AttributeValueGroup />} />
        <Route path="attribute/*" element={<Attribute />} />
        <Route path="purchase-from-other-resources/*" element={<PurchaseFromOtherResources />} />
        <Route path="order-registration-info/*" element={<OrderRegistrationInfo />} />
        <Route path="transportation-type/*" element={<TransportationType />} />
        <Route path="string-value/*" element={<StringValue />} />
        <Route path="long-value/*" element={<LongValue />} />
        <Route path="draft-status-info/*" element={<DraftStatusInfo />} />
        <Route path="draft/*" element={<Draft />} />
        <Route path="draft-factor/*" element={<DraftFactor />} />
        <Route path="draft-used-assurance/*" element={<DraftUsedAssurance />} />
        <Route path="insurance-company-info/*" element={<InsuranceCompanyInfo />} />
        <Route path="currency-exchange-info/*" element={<CurrencyExchangeInfo />} />
        <Route path="suggested-sanction-info/*" element={<SuggestedSanctionInfo />} />
        <Route path="draft-account-info/*" element={<DraftAccountInfo />} />
        <Route path="audit-company-info/*" element={<AuditCompanyInfo />} />
        <Route path="draft-extend/*" element={<DraftExtend />} />
        <Route path="draft-tax/*" element={<DraftTax />} />
        <Route path="document-transaction/*" element={<DocumentTransaction />} />
        <Route path="draft-type/*" element={<DraftType />} />
        <Route path="draft-type-topic-info/*" element={<DraftTypeTopicInfo />} />
        <Route path="credit-type-condition-info/*" element={<CreditTypeConditionInfo />} />
        <Route path="credit-type-condition/*" element={<CreditTypeCondition />} />
        <Route path="draft-type-account-info/*" element={<DraftTypeAccountInfo />} />
        <Route path="draft-certificate-type/*" element={<DraftCertificateType />} />
        <Route path="draft-request-type/*" element={<DraftRequestType />} />
        <Route path="advisor-definition/*" element={<AdvisorDefinition />} />
        <Route path="advisor-definition-deposit/*" element={<AdvisorDefinitionDeposit />} />
        <Route path="transfer-method-management/*" element={<TransferMethodManagement />} />
        <Route path="swift-bic/*" element={<SwiftBic />} />
        <Route path="additional-broker-information/*" element={<AdditionalBrokerInformation />} />
        <Route path="draft-receipt/*" element={<DraftReceipt />} />
        <Route path="payment-currency-rate-type/*" element={<PaymentCurrencyRateType />} />
        <Route path="payment-item-type/*" element={<PaymentItemType />} />
        <Route path="draft-product-info/*" element={<DraftProductInfo />} />
        <Route path="draft-receipt-document-transaction-container/*" element={<DraftReceiptDocumentTransactionContainer />} />
        <Route path="draft-document-transaction-container/*" element={<DraftDocumentTransactionContainer />} />
        <Route path="custom-justification/*" element={<CustomJustification />} />
        <Route path="trade-type-code/*" element={<TradeTypeCode />} />
        <Route path="payment-condition/*" element={<PaymentCondition />} />
        <Route path="custom-justification-child/*" element={<CustomJustificationChild />} />
        <Route path="justification-deduction-amount/*" element={<JustificationDeductionAmount />} />
        <Route path="justification-deduction-amount-part/*" element={<JustificationDeductionAmountPart />} />
        <Route path="justification-deduction-detail/*" element={<JustificationDeductionDetail />} />
        <Route path="draft-custom-justification/*" element={<DraftCustomJustification />} />
        <Route path="basic-info/*" element={<BasicInfo />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
