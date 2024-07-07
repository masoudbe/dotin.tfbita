import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import InsuranceCompanyInfo from './insurance-company-info';
import InsuranceCompanyInfoDetail from './insurance-company-info-detail';
import InsuranceCompanyInfoUpdate from './insurance-company-info-update';
import InsuranceCompanyInfoDeleteDialog from './insurance-company-info-delete-dialog';

const InsuranceCompanyInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<InsuranceCompanyInfo />} />
    <Route path="new" element={<InsuranceCompanyInfoUpdate />} />
    <Route path=":id">
      <Route index element={<InsuranceCompanyInfoDetail />} />
      <Route path="edit" element={<InsuranceCompanyInfoUpdate />} />
      <Route path="delete" element={<InsuranceCompanyInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default InsuranceCompanyInfoRoutes;
