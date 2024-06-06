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
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
