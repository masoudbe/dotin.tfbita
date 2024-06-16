import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AdvisorDefinition from './advisor-definition';
import AdvisorDefinitionDetail from './advisor-definition-detail';
import AdvisorDefinitionUpdate from './advisor-definition-update';
import AdvisorDefinitionDeleteDialog from './advisor-definition-delete-dialog';

const AdvisorDefinitionRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AdvisorDefinition />} />
    <Route path="new" element={<AdvisorDefinitionUpdate />} />
    <Route path=":id">
      <Route index element={<AdvisorDefinitionDetail />} />
      <Route path="edit" element={<AdvisorDefinitionUpdate />} />
      <Route path="delete" element={<AdvisorDefinitionDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AdvisorDefinitionRoutes;
