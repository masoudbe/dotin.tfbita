import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Region from './region';
import Country from './country';
import Location from './location';
import Department from './department';
import Task from './task';
import Employee from './employee';
import Job from './job';
import JobHistory from './job-history';
import OrderRegistrationInfo from './order-registration-info';
import CategoryElement from './category-element';
import Custom from './custom';
import LicenceInfo from './licence-info';
import OrderRegServ from './order-reg-serv';
import Product from './product';
import PurchaseFromOtherResources from './purchase-from-other-resources';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="region/*" element={<Region />} />
        <Route path="country/*" element={<Country />} />
        <Route path="location/*" element={<Location />} />
        <Route path="department/*" element={<Department />} />
        <Route path="task/*" element={<Task />} />
        <Route path="employee/*" element={<Employee />} />
        <Route path="job/*" element={<Job />} />
        <Route path="job-history/*" element={<JobHistory />} />
        <Route path="order-registration-info/*" element={<OrderRegistrationInfo />} />
        <Route path="category-element/*" element={<CategoryElement />} />
        <Route path="custom/*" element={<Custom />} />
        <Route path="licence-info/*" element={<LicenceInfo />} />
        <Route path="order-reg-serv/*" element={<OrderRegServ />} />
        <Route path="product/*" element={<Product />} />
        <Route path="purchase-from-other-resources/*" element={<PurchaseFromOtherResources />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
