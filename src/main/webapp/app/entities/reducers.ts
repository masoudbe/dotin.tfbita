import orderRegistrationInfo from 'app/entities/order-registration-info/order-registration-info.reducer';
import categoryElement from 'app/entities/category-element/category-element.reducer';
import custom from 'app/entities/custom/custom.reducer';
import orderRegServ from 'app/entities/order-reg-serv/order-reg-serv.reducer';
import product from 'app/entities/product/product.reducer';
import licenceInfo from 'app/entities/licence-info/licence-info.reducer';
import purchaseFromOtherResources from 'app/entities/purchase-from-other-resources/purchase-from-other-resources.reducer';
import draft from 'app/entities/draft/draft.reducer';
import serviceTariff from 'app/entities/service-tariff/service-tariff.reducer';
import draftStatusInfo from 'app/entities/draft-status-info/draft-status-info.reducer';
import draftReceipt from 'app/entities/draft-receipt/draft-receipt.reducer';
import draftCustomJustification from 'app/entities/draft-custom-justification/draft-custom-justification.reducer';
import draftFactor from 'app/entities/draft-factor/draft-factor.reducer';
import draftUsedAssurance from 'app/entities/draft-used-assurance/draft-used-assurance.reducer';
import insuranceCompanyInfo from 'app/entities/insurance-company-info/insurance-company-info.reducer';
import advisorDefinition from 'app/entities/advisor-definition/advisor-definition.reducer';
import draftType from 'app/entities/draft-type/draft-type.reducer';
import auditCompanyInfo from 'app/entities/audit-company-info/audit-company-info.reducer';
import draftExtend from 'app/entities/draft-extend/draft-extend.reducer';
import draftTax from 'app/entities/draft-tax/draft-tax.reducer';
import category from 'app/entities/category/category.reducer';
import productType from 'app/entities/product-type/product-type.reducer';
import attributeValue from 'app/entities/attribute-value/attribute-value.reducer';
import typeAttribute from 'app/entities/type-attribute/type-attribute.reducer';
import attributeValueGroup from 'app/entities/attribute-value-group/attribute-value-group.reducer';
import attribute from 'app/entities/attribute/attribute.reducer';
import orderRegService from 'app/entities/order-reg-service/order-reg-service.reducer';
import objectiveCategory from 'app/entities/objective-category/objective-category.reducer';
import objectiveCategoryElement from 'app/entities/objective-category-element/objective-category-element.reducer';
import transportationType from 'app/entities/transportation-type/transportation-type.reducer';
import stringValue from 'app/entities/string-value/string-value.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  orderRegistrationInfo,
  categoryElement,
  custom,
  orderRegServ,
  product,
  licenceInfo,
  purchaseFromOtherResources,
  draft,
  serviceTariff,
  draftStatusInfo,
  draftReceipt,
  draftCustomJustification,
  draftFactor,
  draftUsedAssurance,
  insuranceCompanyInfo,
  advisorDefinition,
  draftType,
  auditCompanyInfo,
  draftExtend,
  draftTax,
  category,
  productType,
  attributeValue,
  typeAttribute,
  attributeValueGroup,
  attribute,
  orderRegService,
  objectiveCategory,
  objectiveCategoryElement,
  transportationType,
  stringValue,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
