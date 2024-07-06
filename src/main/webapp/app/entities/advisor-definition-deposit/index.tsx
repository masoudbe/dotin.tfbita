import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AdvisorDefinitionDeposit from './advisor-definition-deposit';
import AdvisorDefinitionDepositDetail from './advisor-definition-deposit-detail';
import AdvisorDefinitionDepositUpdate from './advisor-definition-deposit-update';
import AdvisorDefinitionDepositDeleteDialog from './advisor-definition-deposit-delete-dialog';

const AdvisorDefinitionDepositRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AdvisorDefinitionDeposit />} />
    <Route path="new" element={<AdvisorDefinitionDepositUpdate />} />
    <Route path=":id">
      <Route index element={<AdvisorDefinitionDepositDetail />} />
      <Route path="edit" element={<AdvisorDefinitionDepositUpdate />} />
      <Route path="delete" element={<AdvisorDefinitionDepositDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AdvisorDefinitionDepositRoutes;
