import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AuditCompanyInfo from './audit-company-info';
import AuditCompanyInfoDetail from './audit-company-info-detail';
import AuditCompanyInfoUpdate from './audit-company-info-update';
import AuditCompanyInfoDeleteDialog from './audit-company-info-delete-dialog';

const AuditCompanyInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AuditCompanyInfo />} />
    <Route path="new" element={<AuditCompanyInfoUpdate />} />
    <Route path=":id">
      <Route index element={<AuditCompanyInfoDetail />} />
      <Route path="edit" element={<AuditCompanyInfoUpdate />} />
      <Route path="delete" element={<AuditCompanyInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AuditCompanyInfoRoutes;
