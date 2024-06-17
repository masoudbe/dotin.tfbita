import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import OrderRegistrationInfo from './order-registration-info';
import CategoryElement from './category-element';
import Custom from './custom';
import OrderRegServ from './order-reg-serv';
import Product from './product';
import LicenceInfo from './licence-info';
import PurchaseFromOtherResources from './purchase-from-other-resources';
import Draft from './draft';
import ServiceTariff from './service-tariff';
import DraftStatusInfo from './draft-status-info';
import DraftReceipt from './draft-receipt';
import DraftCustomJustification from './draft-custom-justification';
import DraftFactor from './draft-factor';
import DraftUsedAssurance from './draft-used-assurance';
import InsuranceCompanyInfo from './insurance-company-info';
import AdvisorDefinition from './advisor-definition';
import DraftType from './draft-type';
import AuditCompanyInfo from './audit-company-info';
import DraftExtend from './draft-extend';
import DraftTax from './draft-tax';
import Category from './category';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="order-registration-info/*" element={<OrderRegistrationInfo />} />
        <Route path="category-element/*" element={<CategoryElement />} />
        <Route path="custom/*" element={<Custom />} />
        <Route path="order-reg-serv/*" element={<OrderRegServ />} />
        <Route path="product/*" element={<Product />} />
        <Route path="licence-info/*" element={<LicenceInfo />} />
        <Route path="purchase-from-other-resources/*" element={<PurchaseFromOtherResources />} />
        <Route path="draft/*" element={<Draft />} />
        <Route path="service-tariff/*" element={<ServiceTariff />} />
        <Route path="draft-status-info/*" element={<DraftStatusInfo />} />
        <Route path="draft-receipt/*" element={<DraftReceipt />} />
        <Route path="draft-custom-justification/*" element={<DraftCustomJustification />} />
        <Route path="draft-factor/*" element={<DraftFactor />} />
        <Route path="draft-used-assurance/*" element={<DraftUsedAssurance />} />
        <Route path="insurance-company-info/*" element={<InsuranceCompanyInfo />} />
        <Route path="advisor-definition/*" element={<AdvisorDefinition />} />
        <Route path="draft-type/*" element={<DraftType />} />
        <Route path="audit-company-info/*" element={<AuditCompanyInfo />} />
        <Route path="draft-extend/*" element={<DraftExtend />} />
        <Route path="draft-tax/*" element={<DraftTax />} />
        <Route path="category/*" element={<Category />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
